package com.ertug.rabbitmq.core.initializer;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelFactory {

    private static final Logger logger = LoggerFactory.getLogger(ChannelFactory.class);

    private Connection connection;

    private ChannelFactory(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try {
            connection = connectionFactory.newConnection();
        } catch (Exception e) {
            logger.error("Connection error with rabbitmq ",e);
            connection = null;
        }
    }

    private static final ChannelFactory channelFactory = new ChannelFactory();

    public static ChannelFactory getInstance(){
        return channelFactory;
    }

    public Connection getConnection() {
        return connection;
    }


}
