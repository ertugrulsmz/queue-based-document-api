package com.ertug.generator;

import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentGenerator {

    private final Logger logger = LoggerFactory.getLogger(DocumentGenerator.class);

    public DocumentQueueItem generateDocument(DocumentQueueItem doc){
        logger.info("Document is being generated for id :"+doc.getId());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doc.setContent(UUID.randomUUID().toString());

        return doc;
    }
}
