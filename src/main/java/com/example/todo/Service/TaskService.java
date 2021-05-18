package com.example.todo.Service;

import com.example.todo.Model.Entity.TaskEntity;
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
        String token = "";
        String username = "";
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        List<TaskEntity> tasks = taskRepository.getTasks(username);

        // Próbáltam a jelszót elrejteni
        List<TaskEntity> tasksWithoutPass = tasks.stream().map(task -> {
            task.getUser().setPassword("*****");
            return task;
        }).collect(Collectors.toList());

        return tasksWithoutPass;
    }
}
