package com.example.todo.Service;

import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Repository.LabelRepository;
import com.example.todo.Repository.TaskRepository;
import com.example.todo.Security.JwtUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private JwtUtil jwtUtil;

    private TaskRepository taskRepository;
    private LabelRepository labelRepository;

    public List<TaskEntity> getTasks(String authorizationHeader) {
        String token = "";
        String username = "";
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        List<TaskEntity> tasks = taskRepository.getTasks(username);
        return tasks;
    }
}
