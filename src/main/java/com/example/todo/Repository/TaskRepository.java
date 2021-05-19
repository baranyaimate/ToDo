package com.example.todo.Repository;

import com.example.todo.Core.service.CoreCRUDService;
import com.example.todo.Model.Entity.TaskEntity;

import java.util.List;

public interface TaskRepository extends CoreCRUDService<TaskEntity> {

    List<TaskEntity> getTasks(String username);
}
