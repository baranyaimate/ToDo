package com.example.todo.service;

import com.example.todo.model.entity.UserEntity;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(),
                user.getIsActive() == 1,
                true,
                true,
                true, new ArrayList<>());
    }
}
