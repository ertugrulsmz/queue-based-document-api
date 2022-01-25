package com.ertug.web.demo.mongo.configuration;

import com.ertug.web.demo.mongo.property.MongoProperty;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.ertug.web.demo")
public class MongoConfig {

    private final MongoProperty mongoProperty;

    public MongoConfig(MongoProperty mongoProperty) {
        this.mongoProperty = mongoProperty;
    }

    @Bean
    public MongoClient mongo() {

        /*ConnectionString connectionString =
                new ConnectionString("mongodb://admin:admin@localhost:27017/" + mongoProperty.getDatabase());
        */

        ConnectionString connectionString =
                new ConnectionString("mongodb://admin:admin@localhost:27017/");


        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), mongoProperty.getDatabase());
    }
}
