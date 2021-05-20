package com.example.todo.controller;

import com.example.todo.model.request.RegistrationRequest;
import com.example.todo.model.response.MessageResponse;
import com.example.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    @ResponseBody
    public MessageResponse registration(RegistrationRequest registrationRequest) {
        return new MessageResponse(userService.registration(registrationRequest));
    }
}
