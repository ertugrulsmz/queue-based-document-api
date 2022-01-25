package com.ertug.web.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDocument implements Serializable {

    private String id;
    private String userId;
    private String name;
    DocumentStatus status;
    Date createdAt;
    private String content;

}
