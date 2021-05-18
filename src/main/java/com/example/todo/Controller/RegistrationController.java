package com.example.todo.Controller;

import com.example.todo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping( "/registration")
    public String Registration(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password) {
        return userService.RegistrationService(username, email, password);
    }
}
