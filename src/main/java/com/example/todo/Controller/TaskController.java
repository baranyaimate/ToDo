package com.example.todo.Controller;

import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Model.Response.TasksResponse;
import com.example.todo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping( "/tasks")
    @ResponseBody
    public List<TasksResponse> getTasks(@RequestHeader String Authorization){
        return taskService.getTasks(Authorization);
    }

    @PostMapping( "/task/{taskId}")
    @ResponseBody
    public TasksResponse getTask(@RequestHeader String Authorization, @PathVariable Long taskId){
        return taskService.getTask(Authorization, taskId);
    }

}
