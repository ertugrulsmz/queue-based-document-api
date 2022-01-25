package com.ertug.web.demo.api.dto.response;

import com.ertug.web.demo.model.UserDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentListResponse {
    private List<UserDocument> createdDocument;
    private List<UserDocument> waitingDocument;
}
