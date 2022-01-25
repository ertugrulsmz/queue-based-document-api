package com.ertug.queue.base;

import java.io.IOException;
import java.io.Serializable;

public interface QueuePublisher {
    <T extends Serializable> void publish(String exchange, String key, T t) throws IOException;
}
