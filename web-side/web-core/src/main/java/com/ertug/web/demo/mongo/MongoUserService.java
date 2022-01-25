package com.ertug.web.demo.mongo;

import com.ertug.web.demo.model.DocumentStatus;
import com.ertug.web.demo.model.UserDocument;
import com.ertug.web.demo.model.UserEntity;
import com.ertug.web.demo.mongo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class MongoUserService {

    private static final Logger logger = LoggerFactory.getLogger(MongoUserService.class);

    private final UserRepository userRepository;

    public MongoUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserEntity user) {
        userRepository.insert(user);
    }

    public UserEntity findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDocument addDocumentForUser(UserEntity user, String documentName) {
        UserDocument userDocument = new UserDocument();

        Date date = new Date(System.currentTimeMillis());

        userDocument.setCreatedAt(date);
        userDocument.setId(UUID.randomUUID().toString());
        userDocument.setName(documentName);
        userDocument.setStatus(DocumentStatus.ON_QUEUE);
        userDocument.setUserId(user.getId());

        user.getDocuments().add(userDocument);
        userRepository.save(user);
        return userDocument;
    }

    public Optional<UserEntity> findById(String id){
        return userRepository.findById(id);
    }

    public void updateDocument(UserDocument userDocument) {
        String userId = userDocument.getUserId();
        Optional<UserEntity> byId = findById(userId);

        if(byId.isEmpty()){
            logger.error(" user with id {} can not found ind db",userId);
            return;
        }

        UserEntity userEntity = byId.get();

        UserDocument userDocument1 = userEntity.getDocuments().get(userEntity.getDocuments().size() - 1);
        userDocument1.setContent(userDocument.getContent());
        userDocument1.setStatus(DocumentStatus.CREATED);

        userRepository.save(userEntity);

    }
}
