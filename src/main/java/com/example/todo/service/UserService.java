package com.example.todo.service;

import com.example.todo.model.entity.UserEntity;
import com.example.todo.model.request.RegistrationRequest;
import com.example.todo.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    public List<String> registration(RegistrationRequest user) {
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

        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            msg.add("Invalid Email address");
        }

        if (passwordStrength(user.getPassword()) < 6) {
            msg.add("Password is too easy");
        }

        if (usernameIsUnique(user.getUsername())) {
            msg.add("Username is not unique");
        }

        if (emailIsUnique(user.getEmail())) {
            msg.add("Email is not unique");
        }

        try {
            if (msg.isEmpty()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(user.getUsername());
                userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
                userEntity.setEmail(user.getEmail());
                userEntity.setIsActive(1);
                userEntity.setCreatedAt(new Date(System.currentTimeMillis()));
                userRepo.save(userEntity);
                msg.add("Successful registration");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return msg;
    }

    private int passwordStrength(String password) {

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

    private boolean usernameIsUnique(String username) {
        return userRepo.countByUsername(username) > 0;
    }

    private boolean emailIsUnique(String email) {
        return userRepo.countByEmail(email) > 0;
    }
}
