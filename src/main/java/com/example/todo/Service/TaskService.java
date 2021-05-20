package com.example.todo.Service;

import com.example.todo.Model.Entity.LabelEntity;
import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Model.Entity.UserEntity;
import com.example.todo.Repository.LabelRepository;
import com.example.todo.Repository.TaskRepository;
import com.example.todo.Repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    public List<TaskEntity> getTasks(String authorizationHeader) {

        String username = getUsername(authorizationHeader);

        List<TaskEntity> tasks = taskRepository.getTasks(username);

        tasks.stream().peek(task -> {
            task.getUser().setPassword(null);
            task.getLabel().stream().peek(labelEntity -> labelEntity.setTaskId(null)).collect(Collectors.toList());
        }).collect(Collectors.toList());


        return tasks;
    }

    public TaskEntity getTask(String authorizationHeader, Long taskId) {

        String username = getUsername(authorizationHeader);

        TaskEntity task = taskRepository.getTask(username, taskId);
        task.getUser().setPassword(null);
        task.getLabel().stream().peek(labelEntity -> labelEntity.setTaskId(null)).collect(Collectors.toList());

        return task;
    }

    public List<String> addTask(String authorizationHeader, TaskEntity taskEntity) {
        ArrayList<String> msg = new ArrayList<>();
        String token = authorizationHeader.substring(7);
        UserEntity user = userRepository.findByUsername(jwtUtil.extractUsername(token));

        taskEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        taskEntity.setUser(user);

        if (taskEntity.getDeadline() == null) {
            msg.add("Deadline is required");
        }
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

    public List<String> updateTask(String authorizationHeader, TaskEntity taskEntity, Long taskId) {
        ArrayList<String> msg = new ArrayList<>();
        String token = authorizationHeader.substring(7);
        UserEntity user = userRepository.findByUsername(jwtUtil.extractUsername(token));
        TaskEntity currentTask = taskRepository.getTask(user.getUsername(), taskId);

        taskEntity.setUpdatedAt(new Date(System.currentTimeMillis()));
        taskEntity.setUser(user);
        taskEntity.setId(currentTask.getId());
        taskEntity.setCreatedAt(currentTask.getCreatedAt());

        if (taskEntity.getDeadline() == null) {
            taskEntity.setDeadline(currentTask.getDeadline());
        }
        if (taskEntity.getDescription() == null) {
            taskEntity.setDescription(currentTask.getDescription());
        }
        if (taskEntity.getIsImportant() == null) {
            taskEntity.setIsImportant(currentTask.getIsImportant());
        }
        if (taskEntity.getName() == null) {
            taskEntity.setName(currentTask.getName());
        }

        System.out.println(taskEntity);

        try {
            taskRepository.update(taskEntity);
            msg.add("Task updated");
        } catch (Exception e) {
            msg.add("Invalid parameters");
        }
        return msg;
    }

    public List<String> deleteTask(String authorizationHeader, Long taskId) {
        ArrayList<String> msg = new ArrayList<>();
        String username = getUsername(authorizationHeader);

        try {
            taskRepository.remove(taskRepository.getTask(username, taskId));
            msg.add("Task deleted");
        } catch (Exception e) {
            msg.add("Invalid task id");
        }
        return msg;
    }

    public List<String> addLabel(String authorization, Long taskId, String name) {
        ArrayList<String> msg = new ArrayList<>();
        String username = getUsername(authorization);

        try{
            TaskEntity task = taskRepository.getTask(username, taskId);
            LabelEntity labelEntity = new LabelEntity();
            labelEntity.setName(name);
            labelEntity.setTaskId(task.getId());
            labelRepository.add(labelEntity);

            msg.add("Label added successful");
        } catch (Exception ex) {
            msg.add("Invalid task id");
        }
        return msg;
    }

    private String getUsername(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return jwtUtil.extractUsername(token);
        }
        return "";
    }

}
