package com.example.todo.Service.Impl;

import com.example.todo.Core.service.impl.CoreCRUDServiceImpl;
import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Service.TaskService;

public class TaskServiceImpl extends CoreCRUDServiceImpl<TaskEntity> implements TaskService {

    @Override
    protected void updateCore(TaskEntity persistedEntity, TaskEntity entity) {
        persistedEntity.setDeadline(entity.getDeadline());
        persistedEntity.setDescription(entity.getDescription());
        persistedEntity.setIsImportant(entity.getIsImportant());
        persistedEntity.setName(entity.getName());
        persistedEntity.setUser(entity.getUser());
        persistedEntity.setUpdatedAt(entity.getUpdatedAt());
    }

    @Override
    protected Class<TaskEntity> getManagedClass() {
        return TaskEntity.class;
    }

}
