package com.example.todo.Service;

import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Model.Response.TasksResponse;
import com.example.todo.Repository.LabelRepository;
import com.example.todo.Repository.TaskRepository;
import com.example.todo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public List<TasksResponse> getTasks(String authorizationHeader) {

        String username = getUsername(authorizationHeader);

        List<TaskEntity> tasks = taskRepository.getTasks(username);

        List<TasksResponse> tasksData = new ArrayList<>();

        tasks.stream().peek(task -> {
            List<String> labels = labelRepository.getLabelsByTask(task.getId(), username);
            task.getUser().setPassword(null);
            tasksData.add(addTaskToTaskResponse(task, labels));
        }).collect(Collectors.toList());

        return tasksData;
    }

    public TasksResponse getTask(String authorizationHeader, Long taskId) {

        String username = getUsername(authorizationHeader);

        TaskEntity task = taskRepository.getTask(username, taskId);
        List<String> labels = labelRepository.getLabelsByTask(taskId, username);
        task.getUser().setPassword(null);

        return addTaskToTaskResponse(task, labels);
    }

    public List<String> addTask(String authorizationHeader, TaskEntity taskEntity) {
        ArrayList<String> msg = new ArrayList<>();

        taskEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        //Pl ezert kene szerintem atirni username helyett userId-ra
        //taskEntity.setUser();

        //TODO: nem tudom hogyan tudok dátumot átadni a postmen-ből😅
        /*if (taskEntity.getDeadline() == null) {
            msg.add("Deadline is required");
        }*/
        if (taskEntity.getDescription() == null || taskEntity.getDescription().length() < 4) {
            msg.add("Description is too short");
        }
        if (taskEntity.getIsImportant() == null) {
            taskEntity.setIsImportant(0);
        }
        if (taskEntity.getIsImportant() > 1) {
            taskEntity.setIsImportant(1);
        }
        if (taskEntity.getDescription() == null || taskEntity.getName().length() < 4) {
            msg.add("Name is too short");
        }

        if (msg.isEmpty()) {
            taskRepository.add(taskEntity);
            msg.add("Task created");
        }
        return msg;
    }

    private String getUsername(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return jwtUtil.extractUsername(token);
        }
        return "";
    }

    private TasksResponse addTaskToTaskResponse(TaskEntity task, List<String> labels) {
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
}
