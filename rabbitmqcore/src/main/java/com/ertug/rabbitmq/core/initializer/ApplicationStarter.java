package com.ertug.rabbitmq.core.initializer;

import com.ertug.queue.base.QueueInitializer;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationStarter {

    private final List<QueueInitializer> queueInitializer;
    private final ApplicationEventPublisher eventPublisher;

    public ApplicationStarter(List<QueueInitializer> queueInitializer, ApplicationEventPublisher eventPublisher) {
        this.queueInitializer = queueInitializer;
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void on(ApplicationStartedEvent applicationStartedEvent){
        queueInitializer.forEach(QueueInitializer::init);
        eventPublisher.publishEvent(new QueueStartedEvent("started"));
    }
}
