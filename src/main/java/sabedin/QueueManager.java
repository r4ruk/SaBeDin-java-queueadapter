package sabedin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import lombok.Data;
import java.util.List;
import java.util.Objects;

@Data
public class QueueManager {
    String queueName;
    IQueryHandler queryHandler;
    ICommandHandler commandHandler;

    public QueueManager(String queueName, IQueryHandler queryHandler, ICommandHandler commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
        this.queueName = queueName;
    }

    public void Listen() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("raruk");
        factory.setPassword("test123");
        factory.setHost("localhost");
        factory.setPort(5672);

        try (Connection readConnection = factory.newConnection();
             Channel readChannel = readConnection.createChannel();
             Connection publishConnection = factory.newConnection();
             Channel publishChannel = publishConnection.createChannel()) {

            readChannel.queueDeclare(this.queueName, true, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                // todo try catch
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
            readChannel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
