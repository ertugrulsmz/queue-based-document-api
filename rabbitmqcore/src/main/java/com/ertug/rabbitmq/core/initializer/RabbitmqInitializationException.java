package com.ertug.rabbitmq.core.initializer;

public class RabbitmqInitializationException extends RuntimeException {
    public RabbitmqInitializationException(String message, Exception e) {
        super(message,e);
    }
}
