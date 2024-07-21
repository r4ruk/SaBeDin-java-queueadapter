package sabedin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import lombok.Data;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Data
public class QueueManager {
    private final String queueName;
    private final IQueryHandler queryHandler;
    private final ICommandHandler commandHandler;
    private final ExecutorService executorService;
    private Connection readConnection;
    private Channel readChannel;
    private Connection publishConnection;
    private Channel publishChannel;

    public QueueManager(String queueName, IQueryHandler queryHandler, ICommandHandler commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
        this.queueName = queueName;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void startListening() {
        executorService.submit(this::listen);
    }

    public void stopListening() {
        try {
            if (readChannel != null) readChannel.close();
            if (readConnection != null) readConnection.close();
            if (publishChannel != null) publishChannel.close();
            if (publishConnection != null) publishConnection.close();
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("raruk");
        factory.setPassword("test123");
        factory.setHost("localhost");
        factory.setPort(5672);

        default_exception_handler asdf = new default_exception_handler();
        factory.setExceptionHandler(asdf);

        try {
            readConnection = factory.newConnection();
            readChannel = readConnection.createChannel();
            publishConnection = factory.newConnection();
            publishChannel = publishConnection.createChannel();

            readChannel.queueDeclare(this.queueName, true, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                ObjectMapper objectMapper = new ObjectMapper();
                QueueRequestMessage queueRequestMessage = objectMapper.readValue(message, QueueRequestMessage.class);

                if(delivery.getProperties().getMessageId() != null &&
                        !Objects.equals(delivery.getProperties().getMessageId(), "")) {

                    List<String> returnlist = this.queryHandler.handle(queueRequestMessage.getBody());

                    publishChannel.basicPublish("",
                            queueName+"Response",
                            new AMQP.BasicProperties().builder().replyTo(delivery.getProperties().getMessageId()).build(),
                            objectMapper.writeValueAsString(returnlist).getBytes());

                } else {
                    this.commandHandler.handle(queueRequestMessage.getBody());
                }
            };

            readChannel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

            System.out.println("Listening on queue for message...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
