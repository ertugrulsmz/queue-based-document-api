package com.ertug.rabbitmq.core.initializer;

import com.ertug.queue.base.QueueInitializer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConsumerInitializer implements QueueInitializer {
    private final String queueName;

    public RabbitMqConsumerInitializer(String queueName) {
        this.queueName = queueName;
    }

    private void declareQueues(Connection connection) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, true, false, false, null);
        channel.close();
    }

    @Override
    public void init() {
        ChannelFactory channelFactory = ChannelFactory.getInstance();
        Connection connection = channelFactory.getConnection();

        try {
            declareQueues(connection);
        } catch (Exception e){
            throw new RabbitmqInitializationException("Rabbitmq listener can not be initialized",e);
        }

    }
}
