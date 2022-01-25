package com.ertug.queue.shared.queueshared.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class DocumentQueueItem implements Serializable {
    private String id;
    private String userId;
    private String name;
    private String content;
}
