package com.ertug.web.mq.publisher;

import com.ertug.queue.base.QueuePublisher;
import com.ertug.rabbitmq.core.property.QueueProperty;
import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class WebDocumentPublisher implements IWebDocumentPublisher {
    private final QueuePublisher queuePublisher;
    private final QueueProperty queueProperty;

    public WebDocumentPublisher(QueuePublisher queuePublisher, QueueProperty queueProperty) {
        this.queuePublisher = queuePublisher;
        this.queueProperty = queueProperty;
    }

    @Override
    public void publish(DocumentQueueItem documentItem) throws IOException {
        queuePublisher.publish(queueProperty.getExchangeName(),
                queueProperty.getKeyName(), documentItem);
    }
}
