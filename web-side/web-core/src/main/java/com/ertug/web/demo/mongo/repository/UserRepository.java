package com.ertug.web.demo.mongo.repository;

import com.ertug.web.demo.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
