package com.example.todo.Service;

import com.example.todo.Repository.LabelRepository;
import com.example.todo.Repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private LabelRepository labelRepository;

    private String init(){
        return null;
    }
}
