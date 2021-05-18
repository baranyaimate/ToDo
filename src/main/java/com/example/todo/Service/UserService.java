package com.example.todo.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public String RegistrationService(String username, String email, String password) {
        String msg = "";
        
        if (username.length() < 4) {
            msg += "Username is too short";
        }

        if (username.length() > 50) {
            msg += "Username is too long";
        }

        if (password.length() < 5) {
            msg += "Password is too short";
        }

        if (password.length() > 36) {
            msg += "Password is too long";
        }

        if (email.length() < 5) {
            msg += "Password is too short";
        }

        if (email.length() > 36) {
            msg += "Password is too long";
        }

        if (passwordStrength(password) < 7) {
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
