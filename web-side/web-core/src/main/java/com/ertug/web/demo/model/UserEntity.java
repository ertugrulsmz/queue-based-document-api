package com.ertug.web.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user_collection_name")
@Data
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String name;
    private String surname;
    private List<UserDocument> documents = new ArrayList<>();

}
