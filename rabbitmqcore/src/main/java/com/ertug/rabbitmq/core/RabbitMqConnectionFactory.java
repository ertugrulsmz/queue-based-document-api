package com.ertug.rabbitmq.core;

import com.ertug.rabbitmq.core.property.RabbitMqProperty;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMqConnectionFactory {

    private Connection connection;
    private final RabbitMqProperty property;

    public RabbitMqConnectionFactory(RabbitMqProperty property){
        this.property = property;

    }

    public Connection getConnection() throws IOException, TimeoutException {
        if(connection == null){
            synchronized (this){
                if(connection == null){
                    createConnection();
                }
            }
        }
        return connection;
    }

    //amqp://guest:guest@localhost:5672/
    private void createConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connection = connectionFactory.newConnection(property.getUrl());
    }
}
