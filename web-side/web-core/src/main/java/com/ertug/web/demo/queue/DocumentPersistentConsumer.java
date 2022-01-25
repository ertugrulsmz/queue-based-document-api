package com.ertug.web.demo.queue;

import com.ertug.queue.base.QueueObserver;
import com.ertug.web.demo.model.DocumentStatus;
import com.ertug.web.demo.model.UserDocument;
import com.ertug.web.demo.mongo.MongoUserService;
import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;
import org.springframework.stereotype.Service;

@Service
public class DocumentPersistentConsumer implements QueueObserver<DocumentQueueItem> {

    private final MongoUserService mongoUserService;

    public DocumentPersistentConsumer(MongoUserService mongoUserService) {
        this.mongoUserService = mongoUserService;
    }

    @Override
    public void processMessage(DocumentQueueItem documentItem) {
        UserDocument gDocument = UserDocument.builder()
                .id(documentItem.getId())
                .userId(documentItem.getUserId())
                .content(documentItem.getContent())
                .status(DocumentStatus.CREATED)
                .build();

        mongoUserService.updateDocument(gDocument);
    }
}
