package com.example.todo.Controller;

import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping( "/tasks")
    @ResponseBody
    public List<TaskEntity> getTasks(@RequestHeader String Authorization){
        return taskService.getTasks(Authorization);
    }

}