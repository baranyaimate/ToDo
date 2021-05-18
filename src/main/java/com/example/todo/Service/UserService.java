package com.example.todo.Service;

import com.example.todo.Model.Entity.UserEntity;
import com.example.todo.Model.Request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    public ArrayList<String> RegistrationService(RegistrationRequest user) {
        ArrayList<String> msg = new ArrayList<>();
        
        if (user.getUsername().length() < 4) {
            msg.add("Username is too short");
        }

        if (user.getUsername().length() > 50) {
            msg.add("Username is too long");
        }

        if (user.getPassword().length() < 5) {
            msg.add("Password is too short");
        }

        if (user.getPassword().length() > 36) {
            msg.add("Password is too long");
        }

        if (user.getEmail().length() < 5) {
            msg.add("Email is too short");
        }

        if (user.getEmail().length() > 36) {
            msg.add("Email is too long");
        }

        if (passwordStrength(user.getPassword()) < 7) {
            msg.add("Password is too easy");
        }

        if (msg.isEmpty()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(passwordEncoder.encode(user.getUsername()));
            userEntity.setEmail(user.getEmail());
            userEntity.setIsActive(1);
            userEntity.setCreatedAt(new Date(System.currentTimeMillis()));

        }

        return msg;
    }

    private static int passwordStrength(String password) {

        //total score of password
        int passwordScore = 0;

        //password length is longer than 10 character add +2 score
        if (password.length() >= 10) {
            passwordScore += 2;
        } else {
            passwordScore++;
        }

        //if it contains one digit, add 2 to total score
        if (password.matches("(?=.*[0-9]).*")) passwordScore += 2;

        //if it contains one lower case letter, add 2 to total score
        if (password.matches("(?=.*[a-z]).*")) passwordScore += 2;

        //if it contains one upper case letter, add 2 to total score
        if (password.matches("(?=.*[A-Z]).*")) passwordScore += 2;

        //if it contains one special character, add 2 to total score
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) passwordScore += 2;

        return passwordScore;
    }
}
