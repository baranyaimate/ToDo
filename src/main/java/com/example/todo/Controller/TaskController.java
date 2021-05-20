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

    @PostMapping("/tasks")
    @ResponseBody
    public List<TasksResponse> getTasks(@RequestHeader String authorization) {
        return taskService.getTasks(authorization);
    }

    @PostMapping("/task/{taskId}")
    @ResponseBody
    public TasksResponse getTask(@RequestHeader String authorization, @PathVariable Long taskId) {
        return taskService.getTask(authorization, taskId);
    }

    @PostMapping("/task/add")
    @ResponseBody
    public List<String> addTask(@RequestHeader String authorization, TaskEntity taskEntity) {
        return taskService.addTask(authorization, taskEntity);
    }

    @PostMapping("/task/delete/{taskId}")
    @ResponseBody
    public List<String> deleteTask(@RequestHeader String authorization, @PathVariable Long taskId) {
        return taskService.deleteTask(authorization, taskId);
    }

    @PostMapping("/task/update")
    @ResponseBody
    public List<String> updateTask(@RequestHeader String authorization, TaskEntity taskEntity) {
        return taskService.updateTask(authorization, taskEntity);
    }

}
