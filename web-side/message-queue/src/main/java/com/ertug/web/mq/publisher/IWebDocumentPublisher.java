package com.ertug.web.mq.publisher;

import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;

import java.io.IOException;

public interface IWebDocumentPublisher {
    void publish(DocumentQueueItem documentItem) throws IOException;
}
