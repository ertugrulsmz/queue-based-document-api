package com.ertug.queue;

import com.ertug.generator.DocumentGenerator;
import com.ertug.queue.base.QueueObserver;
import com.ertug.queue.base.QueuePublisher;
import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;
import com.ertug.rabbitmq.core.property.QueueProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DocumentServiceObserver implements QueueObserver<DocumentQueueItem> {

    private final Logger logger = LoggerFactory.getLogger(DocumentServiceObserver.class);
    private final QueuePublisher publisher;
    private final DocumentGenerator generator;
    private final QueueProperty property;

    public DocumentServiceObserver(QueuePublisher publisher, DocumentGenerator generator, QueueProperty property) {
        this.publisher = publisher;
        this.generator = generator;
        this.property = property;
    }


    @Override
    public void processMessage(DocumentQueueItem documentItem) {
        DocumentQueueItem documentItem1 = generator.generateDocument(documentItem);
        try {
            publisher.publish(property.getExchangeName(), property.getKeyName(), documentItem1);
        } catch (IOException e) {
            logger.error("Document can not be published to queue",e);
        }
    }
}
