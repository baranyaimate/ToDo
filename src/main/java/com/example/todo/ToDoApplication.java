package com.example.todo;

import com.example.todo.Model.UserEntity;
import com.example.todo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ToDoApplication {

    @Autowired
    private UserRepository repository;


    /*
    *  Ezzel feltölthetjük az adatbázist 3 rekorddal
    *
    @PostConstruct
    public void initUsers() {
        List<UserEntity> users = Stream.of(
                new UserEntity(1l, "spring1", "password1", "valami1@gmail.com", 1, new Date(System.currentTimeMillis())),
                new UserEntity(1l, "spring2", "password2", "valami2@gmail.com", 1, new Date(System.currentTimeMillis())),
                new UserEntity(1l, "spring3", "password3", "valami3@gmail.com", 1, new Date(System.currentTimeMillis()))
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

}
