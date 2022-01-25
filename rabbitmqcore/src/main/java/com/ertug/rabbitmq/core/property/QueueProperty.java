package com.ertug.rabbitmq.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "queue")
public class QueueProperty {
    private String queueNameListen;
    private String exchangeName;
    private String keyName;
    private String queueNamePublish;
}
