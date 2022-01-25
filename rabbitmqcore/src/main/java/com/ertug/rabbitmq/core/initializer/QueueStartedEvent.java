package com.ertug.rabbitmq.core.initializer;

import org.springframework.context.ApplicationEvent;

public class QueueStartedEvent extends ApplicationEvent {
    public QueueStartedEvent(Object source) {
        super(source);
    }
}
