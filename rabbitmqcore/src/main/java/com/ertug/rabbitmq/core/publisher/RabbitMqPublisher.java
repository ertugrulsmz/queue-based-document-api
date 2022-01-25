package com.ertug.rabbitmq.core.publisher;

import com.ertug.queue.base.QueuePublisher;
import com.ertug.rabbitmq.core.RabbitMqConnectionFactory;
import com.ertug.rabbitmq.core.util.CastingUtil;
import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMqPublisher implements QueuePublisher {

    private final Channel channel;

    public RabbitMqPublisher(RabbitMqConnectionFactory rabbitMqConnectionFactory)
            throws IOException, TimeoutException {
        channel = rabbitMqConnectionFactory.getConnection().createChannel();
    }


    @Override
    public <T extends Serializable> void publish(String exchange, String key, T t) throws IOException {
        byte[] bytes = CastingUtil.objectToByteArray(t);
        channel.basicPublish(exchange, key, null, bytes);
    }
}

