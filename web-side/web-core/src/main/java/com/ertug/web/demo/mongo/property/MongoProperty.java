package com.ertug.web.demo.mongo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "mongo")
@Data
@EnableConfigurationProperties(MongoProperty.class)
public class MongoProperty {
    private String userCollectionName;
    private String database;
}
