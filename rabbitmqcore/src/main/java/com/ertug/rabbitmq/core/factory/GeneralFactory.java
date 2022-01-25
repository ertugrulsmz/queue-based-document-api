package com.ertug.rabbitmq.core.factory;

import com.ertug.queue.base.QueueInitializer;
import com.ertug.rabbitmq.core.initializer.RabbitMqConsumerInitializer;
import com.ertug.rabbitmq.core.initializer.RabbitMqPublisherInitializer;
import com.ertug.rabbitmq.core.property.QueueProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralFactory {

    private final QueueProperty queueProperty;

    public GeneralFactory(QueueProperty queueProperty) {
        this.queueProperty = queueProperty;
    }

    @Bean
    public QueueInitializer publisherInitializer(){
        return new RabbitMqPublisherInitializer(queueProperty.getQueueNamePublish(), queueProperty.getExchangeName(),
                queueProperty.getKeyName());
    }

    @Bean
    public QueueInitializer listenerInitializer(){
        return new RabbitMqConsumerInitializer(queueProperty.getQueueNameListen());
    }
}
