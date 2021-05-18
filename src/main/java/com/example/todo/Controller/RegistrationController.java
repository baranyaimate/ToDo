package com.example.todo.Controller;

import com.example.todo.Model.Request.RegistrationRequest;
import com.example.todo.Model.Response.ResponseTransfer;
import com.example.todo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping( "/registration")
    @ResponseBody
    public ResponseTransfer Registration(@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseTransfer(userService.RegistrationService(registrationRequest));
    }
}
