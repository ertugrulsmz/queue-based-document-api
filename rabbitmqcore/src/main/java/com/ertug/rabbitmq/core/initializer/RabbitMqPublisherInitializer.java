package com.ertug.rabbitmq.core.initializer;

import com.ertug.queue.base.QueueInitializer;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqPublisherInitializer implements QueueInitializer {

    private final String queueName;
    private final String exchangeName;
    private final String keyName;

    public RabbitMqPublisherInitializer(String queueName, String exchangeName, String keyName) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.keyName = keyName;
    }

    private void declareExchange(Connection connection) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT , true);
        channel.close();
    }

    private void declareQueues(Connection connection) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, true, false, false, null);
        channel.close();
    }

    private void declareBindings(Connection connection) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.queueBind(queueName,exchangeName,keyName);
        channel.close();
    }

    @Override
    public void init()  {

        try {
            ChannelFactory channelFactory = ChannelFactory.getInstance();
            Connection connection = channelFactory.getConnection();

            declareExchange(connection);
            declareQueues(connection);
            declareBindings(connection);

        } catch (Exception e) {
           throw new RabbitmqInitializationException("Rabbitmq publisher can not be initialized",e);
        }

    }

    /**
    private void declareConsumer(Connection connection) throws IOException {
        Channel channel = connection.createChannel();

        channel.basicConsume("my-pdf-queue", true, ((consumerTag, message) -> {
            System.out.println("\n\n ============ Sports Queue ==========");
            System.out.println(consumerTag);
            System.out.println("SportsQ: " + new String(message.getBody()));
            System.out.println(message.getEnvelope());
        }), consumerTag -> {
            System.out.println(consumerTag);
        });
    }*/



}
