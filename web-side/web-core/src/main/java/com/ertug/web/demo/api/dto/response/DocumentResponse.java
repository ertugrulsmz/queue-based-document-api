package com.ertug.web.demo.api.dto.response;

import com.ertug.web.demo.model.UserDocument;
import lombok.Data;

@Data
public class DocumentResponse {
    private boolean userExistance;
    private boolean documentWaiting;
    private String explanation;
    private UserDocument gDocument;
}
