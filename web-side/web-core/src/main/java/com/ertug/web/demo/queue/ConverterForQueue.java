package com.ertug.web.demo.queue;

import com.ertug.web.demo.model.UserDocument;
import com.ertug.queue.shared.queueshared.dto.DocumentQueueItem;

public class ConverterForQueue {

    private ConverterForQueue(){

    }

    public static DocumentQueueItem convertToDocumentItem(UserDocument userDocument){
        return DocumentQueueItem.builder()
                .id(userDocument.getId())
                .name(userDocument.getName())
                .userId(userDocument.getUserId())
                .build();
    }
}
