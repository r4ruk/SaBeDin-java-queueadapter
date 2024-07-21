package sabedin;

import com.rabbitmq.client.*;

public class default_exception_handler implements ExceptionHandler{

    @Override
    public void handleUnexpectedConnectionDriverException(Connection conn, Throwable exception) {
        System.out.println(exception.toString());
    }

    @Override
    public void handleReturnListenerException(Channel channel, Throwable exception) {
        System.out.println(exception.toString());

    }

    @Override
    public void handleConfirmListenerException(Channel channel, Throwable exception) {
        System.out.println(exception.toString());

    }

    @Override
    public void handleBlockedListenerException(Connection connection, Throwable exception) {

        System.out.println(exception.toString());
    }

    @Override
    public void handleConsumerException(Channel channel, Throwable exception, Consumer consumer, String consumerTag, String methodName) {
        System.out.println(exception.toString());

    }

    @Override
    public void handleConnectionRecoveryException(Connection conn, Throwable exception) {
        System.out.println(exception.toString());

    }

    @Override
    public void handleChannelRecoveryException(Channel ch, Throwable exception) {
        System.out.println(exception.toString());

    }

    @Override
    public void handleTopologyRecoveryException(Connection conn, Channel ch, TopologyRecoveryException exception) {

        System.out.println(exception.toString());
    }
}
