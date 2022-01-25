package com.ertug.queue.base;

import java.io.IOException;
import java.io.Serializable;

public interface QueueConsumer {
    public <T extends Serializable> void consume(String queueName, QueueObserver<T> observer) throws IOException;
}
