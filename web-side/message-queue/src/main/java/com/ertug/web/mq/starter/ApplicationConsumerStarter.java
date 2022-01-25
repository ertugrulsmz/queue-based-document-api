package com.ertug.web.mq.starter;

import com.ertug.queue.base.QueueConsumer;
import com.ertug.queue.base.QueueObserver;
import com.ertug.rabbitmq.core.initializer.QueueStartedEvent;
import com.ertug.rabbitmq.core.property.QueueProperty;
import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;
import org.springframework.context.event.EventListener;

import java.io.IOException;

public class ApplicationConsumerStarter {

    private final QueueConsumer consumer;
    private final QueueObserver<DocumentQueueItem> queueObserver;
    private final QueueProperty property;

    public ApplicationConsumerStarter
            (QueueConsumer consumer, QueueObserver<DocumentQueueItem> queueConsumer,
             QueueProperty property) {
        this.consumer = consumer;
        this.queueObserver = queueConsumer;
        this.property = property;
    }

    @EventListener
    public void on(QueueStartedEvent event) throws IOException {
        consumer.consume(property.getQueueNameListen(), queueObserver);
    }
}
