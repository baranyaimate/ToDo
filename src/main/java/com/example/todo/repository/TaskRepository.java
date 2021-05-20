package com.example.todo.repository;

import com.example.todo.core.service.CoreCRUDService;
import com.example.todo.model.entity.TaskEntity;

import java.util.List;

public interface TaskRepository extends CoreCRUDService<TaskEntity> {

    List<TaskEntity> getTasks(String username);

    TaskEntity getTask(String username, Long taskId);
}
