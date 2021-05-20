package com.example.todo.Controller;

import com.example.todo.Model.Request.RegistrationRequest;
import com.example.todo.Model.Response.MessageResponse;
import com.example.todo.Service.UserService;
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
        return new MessageResponse(userService.Registration(registrationRequest));
    }
}
