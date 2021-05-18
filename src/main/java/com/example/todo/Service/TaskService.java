package com.example.todo.Service;

import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Repository.LabelRepository;
import com.example.todo.Repository.TaskRepository;
import com.example.todo.Security.JwtUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LabelRepository labelRepository;

    private String init(){
        return null;
    }

    public JSONObject getTasks(String token) {
        jwtUtil.extractUsername(token);
        List<TaskEntity> tasks = taskRepository.getTasks(jwtUtil.extractUsername(token));

        JSONArray tasksJsonArray = new JSONArray();
        for (TaskEntity task : tasks) {
            tasksJsonArray.put(task.getId());
            tasksJsonArray.put(task.getName());
            tasksJsonArray.put(task.getUser().getUsername());
        }

        return new JSONObject().put("result", tasksJsonArray);
    }
}
