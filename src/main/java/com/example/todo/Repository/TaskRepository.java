package com.example.todo.Repository;

import com.example.todo.Core.service.CoreCRUDService;
import com.example.todo.Model.Entity.TaskEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CoreCRUDService<TaskEntity> {

}
