package com.ertug.starter;

import com.ertug.queue.base.QueueConsumer;
import com.ertug.queue.base.QueueObserver;
import com.ertug.rabbitmq.core.initializer.QueueStartedEvent;
import com.ertug.rabbitmq.core.property.QueueProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;

@Service
public class ApplicationConsumerStarter<T extends Serializable> {

    private final QueueConsumer consumer;
    private final QueueObserver<T> queueObserver;
    private final QueueProperty property;

    public ApplicationConsumerStarter(QueueConsumer consumer, QueueObserver<T> queueObserver, QueueProperty property) {
        this.consumer = consumer;
        this.queueObserver = queueObserver;
        this.property = property;
    }

    @EventListener
    public void on(QueueStartedEvent event) throws IOException {
        consumer.consume(property.getQueueNameListen(), queueObserver);
    }
}
