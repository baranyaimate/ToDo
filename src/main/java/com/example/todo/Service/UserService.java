package com.example.todo.Service;

import com.example.todo.Model.Request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String RegistrationService(RegistrationRequest user) {
        String msg = "";
        
        if (user.getUsername().length() < 4) {
            msg += "Username is too short";
        }

        if (user.getUsername().length() > 50) {
            msg += "Username is too long";
        }

        if (user.getPassword().length() < 5) {
            msg += "Password is too short";
        }

        if (user.getPassword().length() > 36) {
            msg += "Password is too long";
        }

        if (user.getEmail().length() < 5) {
            msg += "Password is too short";
        }

        if (user.getEmail().length() > 36) {
            msg += "Password is too long";
        }

        if (passwordStrength(user.getPassword()) < 7) {
            msg += "Password is too easy";
        }

        if (msg.isEmpty()) {
            return "Minden Oké";
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
