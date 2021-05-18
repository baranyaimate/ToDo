package com.example.todo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     *
     *  Egy felhasználó léterhozása
     *  Mivel a regisztráció még nincs kész
     *
     */

    /*
    @PostConstruct
    public void initUsers() {
        UserEntity admin = new UserEntity(1l, "admin",
        passwordEncoder.encode("admin"), "admin@gmail.com", 0,
        new Date(System.currentTimeMillis()));
        repository.save(admin);
    }
    */

}
