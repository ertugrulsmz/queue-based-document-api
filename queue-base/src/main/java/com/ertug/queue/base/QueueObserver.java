package com.ertug.queue.base;

public interface QueueObserver<T> {
    void processMessage(T t);
}
