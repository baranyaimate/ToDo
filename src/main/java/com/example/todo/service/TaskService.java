package com.example.todo.service;

import com.example.todo.model.entity.LabelEntity;
import com.example.todo.model.entity.TaskEntity;
import com.example.todo.model.entity.UserEntity;
import com.example.todo.repository.LabelRepository;
import com.example.todo.repository.TaskRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskEntity> getTasks() {
        String username = getUsername();

        List<TaskEntity> tasks = taskRepository.getTasks(username);

        tasks.forEach(task -> {
            task.getUser().setPassword(null);
            task.getLabel().forEach(labelEntity -> labelEntity.setTask(null));
        });

        return tasks;
    }

    public TaskEntity getTask(Long taskId) {
        String username = getUsername();

        TaskEntity task = taskRepository.getTask(username, taskId);
        task.getUser().setPassword(null);
        task.getLabel().forEach(labelEntity -> labelEntity.setTask(null));

        return task;
    }

    public List<String> addTask(TaskEntity taskEntity) {
        UserEntity user = userRepository.findByUsername(getUsername());

        taskEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        taskEntity.setUser(user);

        ArrayList<String> msg = new ArrayList<>(taskValidation(taskEntity));

        if (msg.isEmpty()) {
            taskRepository.add(taskEntity);
            msg.add("Task created successfully");
        }
        return msg;
    }

    public List<String> updateTask(TaskEntity taskEntity, Long taskId) {
        UserEntity user = userRepository.findByUsername(getUsername());
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

        ArrayList<String> msg = new ArrayList<>(taskValidation(taskEntity));

        try {
            taskRepository.update(taskEntity);
            msg.add("Task updated successfully");
        } catch (Exception e) {
            msg.add("Invalid parameters");
        }
        return msg;
    }

    public List<String> deleteTask(Long taskId) {
        ArrayList<String> msg = new ArrayList<>();
        String username = getUsername();

        try {
            taskRepository.remove(taskRepository.getTask(username, taskId));
            msg.add("Task deleted successfully");
        } catch (Exception e) {
            msg.add("Invalid task id");
        }
        return msg;
    }

    public List<String> addLabel(Long taskId, String name) {
        ArrayList<String> msg = new ArrayList<>();
        String username = getUsername();

        if (name == null || name.equals("")) {
            msg.add("Label name is required");
        } else if (name.length() > 40) {
            msg.add("Label name is too long");
        }

        try {
            TaskEntity task = taskRepository.getTask(username, taskId);

            if (task.getLabel().size() < 10) {
                LabelEntity labelEntity = new LabelEntity();
                labelEntity.setName(name);
                labelEntity.setTask(task);
                labelRepository.add(labelEntity);

                msg.add("Label added successfully");
            } else {
                msg.add("You have reached the max number of the labels");
            }
        } catch (Exception ex) {
            msg.add("Invalid task id");
        }
        return msg;
    }

    public List<String> removeLabel(Long labelId) {
        ArrayList<String> msg = new ArrayList<>();
        String username = getUsername();

        try {
            LabelEntity labelEntity = labelRepository.findById(labelId);
            TaskEntity taskEntity = taskRepository.findById(labelEntity.getId());

            if (taskEntity.getUser().getUsername().equals(username)) {
                labelRepository.remove(labelEntity);
                msg.add("Label removed successfully");
            } else {
                msg.add("Invalid label id");
            }
        } catch (Exception e) {
            msg.add("Invalid label id");
        }
        return msg;
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "";
    }

    private ArrayList<String> taskValidation(TaskEntity taskEntity) {
        ArrayList<String> msg = new ArrayList<>();

        if (taskEntity.getDescription() == null || taskEntity.getDescription().length() < 4) {
            msg.add("Description is too short");
        } else if (taskEntity.getDescription().length() > 255) {
            msg.add("Description is too long");
        }

        if (taskEntity.getName() == null || taskEntity.getName().length() < 3) {
            msg.add("Task name is too short");
        } else if (taskEntity.getName().length() > 50) {
            msg.add("Task name is too long");
        }

        if (taskEntity.getDeadline() == null) {
            msg.add("Deadline is required");
        } else if (taskEntity.getDeadline().before(new Date(System.currentTimeMillis()))) {
            msg.add("The deadline is not correct");
        }

        if (taskEntity.getIsImportant() == null || taskEntity.getIsImportant() < 0) {
            taskEntity.setIsImportant(0);
        } else if (taskEntity.getIsImportant() > 1) {
            taskEntity.setIsImportant(1);
        }

        return msg;
    }
}
