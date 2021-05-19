package com.example.todo.Service;

import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Model.Response.TasksResponse;
import com.example.todo.Repository.LabelRepository;
import com.example.todo.Repository.TaskRepository;
import com.example.todo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LabelRepository labelRepository;

    public List<TaskEntity> getTasks(String authorizationHeader) {

        String username = getUsername(authorizationHeader);

        List<TaskEntity> tasks = taskRepository.getTasks(username);

        return tasks.stream().peek(task -> task.getUser().setPassword(null)).collect(Collectors.toList());
    }

    public TasksResponse getTask(String authorizationHeader, Long taskId) {

        String username = getUsername(authorizationHeader);

        TaskEntity task = taskRepository.getTask(username, taskId);
        List<String> labels = labelRepository.getLabelsByTask(taskId, username);
        task.getUser().setPassword(null);

        TasksResponse taskData =  new TasksResponse();

        taskData.setId(task.getId());
        taskData.setName(task.getName());
        taskData.setDeadline(task.getDeadline());
        taskData.setDescription(task.getDescription());
        taskData.setIsImportant(task.getIsImportant());
        taskData.setUser(task.getUser());
        taskData.setCreatedAt(task.getCreatedAt());
        taskData.setUpdatedAt(task.getUpdatedAt());
        taskData.setLabel(labels);

        return taskData;
    }

    private String getUsername(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return jwtUtil.extractUsername(token);
        }
        return "";
    }
}
