package com.example.todo.Controller;

import com.example.todo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping( "/tasks")
    public String getTasks(String token){
        return taskService.getTasks(token).toString();
    }

}
