package com.ertug.web.demo.api;

import com.ertug.web.demo.api.dto.response.DocumentListResponse;
import com.ertug.web.demo.api.dto.response.DocumentResponse;
import com.ertug.web.demo.model.UserEntity;
import com.ertug.web.demo.api.dto.request.DocumentRequest;
import com.ertug.web.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserEntity userEntity){
        userService.registerUser(userEntity);
    }

    @PostMapping("/create_document/{username}")
    public DocumentResponse createDocument
            (@PathVariable String username,
                                           @RequestBody DocumentRequest documentRequest) throws IOException {

        return userService.addDocumentForUser(username, documentRequest);
    }


    @GetMapping("/get_document/{username}")
    public DocumentListResponse getDocumentList(@PathVariable String username){
        return userService.getDocumentList(username);
    }


}
