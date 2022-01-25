package com.ertug.rabbitmq.core.consumer;

import com.ertug.queue.base.QueueConsumer;
import com.ertug.queue.base.QueueObserver;
import com.ertug.rabbitmq.core.RabbitMqConnectionFactory;
import com.ertug.rabbitmq.core.util.CastingUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMqConsumer implements QueueConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqConsumer.class);

    private final Channel channel;

    public RabbitMqConsumer(RabbitMqConnectionFactory rabbitMqConnectionFactory)
            throws IOException, TimeoutException {
        channel = rabbitMqConnectionFactory.getConnection().createChannel();
    }

    public <T extends Serializable> void consume(String queueName, QueueObserver<T> observer) throws IOException {

        channel.basicConsume(queueName, true, ((consumerTag, message) -> {

            byte[] body = message.getBody();
            T t;
            try {
                t = CastingUtil.byteArrayToObject(body);
                observer.processMessage(t);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }), consumerTag -> System.out.println("consumer tag error : " + consumerTag));

    }
}
