package com.example.todo.repository.impl;

import com.example.todo.core.service.impl.CoreCRUDServiceImpl;
import com.example.todo.model.entity.TaskEntity;
import com.example.todo.repository.TaskRepository;
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

    @Override
    public TaskEntity getTask(String username, Long taskId) {
        return entityManager.createQuery("SELECT t FROM TaskEntity t WHERE t.user.username = :username AND t.taskId = :taskId", TaskEntity.class)
                .setParameter("username", username)
                .setParameter("taskId", taskId)
                .getSingleResult();
    }
}
