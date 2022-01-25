package com.ertug.web.demo.queue;

import com.ertug.queue.base.QueueConsumer;
import com.ertug.queue.base.QueueObserver;
import com.ertug.rabbitmq.core.property.QueueProperty;
import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;
import com.ertug.web.mq.starter.ApplicationConsumerStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueFactory {

    @Bean
    public ApplicationConsumerStarter webDocumentConsumer
            (QueueConsumer queueConsumer,
             QueueProperty queueProperty,
             QueueObserver<DocumentQueueItem> queueObserver) {

        return new ApplicationConsumerStarter(queueConsumer, queueObserver, queueProperty);
    }


}
