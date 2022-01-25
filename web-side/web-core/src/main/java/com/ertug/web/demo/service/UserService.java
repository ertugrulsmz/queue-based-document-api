package com.ertug.web.demo.service;

import com.ertug.web.demo.api.dto.response.DocumentListResponse;
import com.ertug.web.demo.api.dto.response.DocumentResponse;
import com.ertug.web.demo.model.DocumentStatus;
import com.ertug.web.demo.model.UserDocument;
import com.ertug.web.demo.mongo.MongoUserService;
import com.ertug.web.demo.model.UserEntity;
import com.ertug.web.demo.queue.ConverterForQueue;
import com.ertug.web.demo.api.dto.request.DocumentRequest;
import com.ertug.web.mq.publisher.IWebDocumentPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final MongoUserService mongoUserService;
    private final IWebDocumentPublisher publisher;

    public UserService(MongoUserService mongoUserService, IWebDocumentPublisher publisher) {
        this.mongoUserService = mongoUserService;
        this.publisher = publisher;
    }

    public void registerUser(UserEntity userEntity) {
        mongoUserService.addUser(userEntity);
    }

    public UserEntity findByUserName(String username) {
        return mongoUserService.findByUserName(username);
    }


    public DocumentResponse addDocumentForUser(String username, DocumentRequest documentRequest) throws IOException {
        UserEntity byUserName = this.findByUserName(username);
        if (byUserName == null) {
            return getUserNotExistDocumentResponse();
        }

        if (byUserName.getDocuments() == null || byUserName.getDocuments().isEmpty()) {
            return createAndPutIntoQueue(documentRequest, byUserName);
        }

        UserDocument userDocument = byUserName.getDocuments().get(byUserName.getDocuments().size()-1);
        if (userDocument.getStatus() == DocumentStatus.ON_QUEUE) {
            return getDocumentWaitingInQueueResponse(userDocument);
        }

        return createAndPutIntoQueue(documentRequest, byUserName);
    }

    public DocumentListResponse getDocumentList(String username){
        UserEntity byUserName = mongoUserService.findByUserName(username);

        List<UserDocument> onqueue = byUserName.getDocuments().stream().
                filter(x -> x.getStatus() == DocumentStatus.ON_QUEUE).collect(Collectors.toList());

        List<UserDocument> created = byUserName.getDocuments().stream().
                filter(x -> x.getStatus() == DocumentStatus.CREATED).collect(Collectors.toList());

        return new DocumentListResponse(created, onqueue);

    }


    private DocumentResponse createAndPutIntoQueue(DocumentRequest documentRequest, UserEntity byUserName) throws IOException {
        UserDocument userDocument = mongoUserService.
                addDocumentForUser(byUserName, documentRequest.getDocumentName());

        publisher.publish(ConverterForQueue.convertToDocumentItem(userDocument));
        return getDocumentCreatedResponse(userDocument);
    }


    private DocumentResponse getDocumentCreatedResponse(UserDocument gDocument1) {
        DocumentResponse response = new DocumentResponse();
        response.setDocumentWaiting(true);
        response.setExplanation("Document with name : " + gDocument1.getName() + " is put into queue");
        response.setUserExistance(true);
        return response;
    }

    private DocumentResponse getUserNotExistDocumentResponse() {
        DocumentResponse response = new DocumentResponse();
        response.setDocumentWaiting(false);
        response.setUserExistance(false);
        response.setExplanation("User does not exist in db");
        return response;
    }

    private DocumentResponse getDocumentWaitingInQueueResponse(UserDocument gDocument1) {
        DocumentResponse response = new DocumentResponse();
        response.setDocumentWaiting(true);
        response.setUserExistance(true);
        response.setExplanation("Document with name : " + gDocument1.getName() + " is waiting on the queue");
        return response;
    }

    private DocumentResponse documentIsProcessed(UserDocument gdocument) {
        DocumentResponse response = new DocumentResponse();
        response.setDocumentWaiting(false);
        response.setUserExistance(true);
        response.setExplanation("Your document is ready");
        response.setGDocument(gdocument);
        return response;
    }
}
