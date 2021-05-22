package com.example.todo.controller;

import com.example.todo.model.entity.TaskEntity;
import com.example.todo.model.response.MessageResponse;
import com.example.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/tasks")
    @ResponseBody
    public List<TaskEntity> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/task/{taskId}")
    @ResponseBody
    public TaskEntity getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    @PostMapping("/task/add")
    @ResponseBody
    public MessageResponse addTask(TaskEntity taskEntity) {
        return new MessageResponse(taskService.addTask(taskEntity));
    }

    @PostMapping("/task/delete/{taskId}")
    @ResponseBody
    public MessageResponse deleteTask(@PathVariable Long taskId) {
        return new MessageResponse(taskService.deleteTask(taskId));
    }

    @PostMapping("/task/update/{taskId}")
    @ResponseBody
    public MessageResponse updateTask(TaskEntity taskEntity, @PathVariable Long taskId) {
        return new MessageResponse(taskService.updateTask(taskEntity, taskId));
    }

    @PostMapping("/task/label/add/{taskId}")
    @ResponseBody
    public MessageResponse addLabel(@PathVariable Long taskId, @RequestParam String name) {
        return new MessageResponse(taskService.addLabel(taskId, name));
    }

    @PostMapping("/task/label/remove/{labelId}")
    @ResponseBody
    public MessageResponse removeLabel(@PathVariable Long labelId) {
        return new MessageResponse(taskService.removeLabel(labelId));
    }

    @PostMapping("/task/label/update/{labelId}")
    @ResponseBody
    public MessageResponse updateLabel(@PathVariable Long labelId, @RequestParam String labelName) {
        return new MessageResponse(taskService.updateLabel(labelId, labelName));
    }

}
