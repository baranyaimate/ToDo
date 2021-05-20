package com.example.todo.Controller;

import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Model.Response.MessageResponse;
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
    public List<TaskEntity> getTasks(@RequestHeader String authorization) {
        return taskService.getTasks(authorization);
    }

    @PostMapping("/task/{taskId}")
    @ResponseBody
    public TaskEntity getTask(@RequestHeader String authorization, @PathVariable Long taskId) {
        return taskService.getTask(authorization, taskId);
    }

    @PostMapping("/task/add")
    @ResponseBody
    public MessageResponse addTask(@RequestHeader String authorization, TaskEntity taskEntity) {
        return new MessageResponse(taskService.addTask(authorization, taskEntity));
    }

    @PostMapping("/task/delete/{taskId}")
    @ResponseBody
    public MessageResponse deleteTask(@RequestHeader String authorization, @PathVariable Long taskId) {
        return new MessageResponse(taskService.deleteTask(authorization, taskId));
    }

    @PostMapping("/task/update/{taskId}")
    @ResponseBody
    public MessageResponse updateTask(@RequestHeader String authorization, TaskEntity taskEntity, @PathVariable Long taskId) {
        return new MessageResponse(taskService.updateTask(authorization, taskEntity, taskId));
    }

    @PostMapping("/task/label/add/{taskId}")
    @ResponseBody
    public MessageResponse addLabel(@RequestHeader String authorization, @PathVariable Long taskId, @RequestParam String name) {
        return new MessageResponse(taskService.addLabel(authorization, taskId, name));
    }

}
