package com.example.todo.Service;

import com.example.todo.Core.service.CoreCRUDService;
import com.example.todo.Model.TaskEntity;
import org.springframework.stereotype.Service;

@Service
public interface TaskService extends CoreCRUDService<TaskEntity> {

}
