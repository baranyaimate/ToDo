package com.example.todo.controller;

import com.example.todo.model.request.AuthRequest;
import com.example.todo.repository.UserRepository;
import com.example.todo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String login() {
        return "Successful logged in!";
    }

    @PostMapping("/authenticate")
    public String generateToken(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            return "Invalid username or password";
        }
        return jwtUtil.generateToken(authRequest.getUsername(), userRepository.findByUsername(authRequest.getUsername()).getId());
    }
}
