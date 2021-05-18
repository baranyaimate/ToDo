package com.example.todo.Repository.Impl;

import com.example.todo.Core.service.impl.CoreCRUDServiceImpl;
import com.example.todo.Model.Entity.TaskEntity;
import com.example.todo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TaskRepositoryImpl extends CoreCRUDServiceImpl<TaskEntity> implements TaskRepository {

    @Autowired
    protected EntityManager entityManager;

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

    @Override
    public List<TaskEntity> getTasks(String username) {
        return entityManager.createQuery("SELECT t FROM TaskEntity t WHERE t.user.username = :username", TaskEntity.class)
                .setParameter("username", username)
                .getResultList();
    }
}
