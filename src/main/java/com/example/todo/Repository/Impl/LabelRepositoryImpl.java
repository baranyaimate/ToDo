package com.example.todo.Repository.Impl;

import com.example.todo.Core.service.impl.CoreCRUDServiceImpl;
import com.example.todo.Model.Entity.LabelEntity;
import com.example.todo.Repository.LabelRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LabelRepositoryImpl extends CoreCRUDServiceImpl<LabelEntity> implements LabelRepository {

    @Override
    protected void updateCore(LabelEntity persistedEntity, LabelEntity entity) {
        persistedEntity.setName(entity.getName());
        persistedEntity.setTask(entity.getTask());
    }

    @Override
    protected Class<LabelEntity> getManagedClass() {
        return LabelEntity.class;
    }

    @Override
    public List<String> getLabelsByTask(Long TaskId, String username) {
        return entityManager.createQuery("SELECT t.name FROM LabelEntity t WHERE t.task.taskId = :taskId AND t.task.user.username = :username", String.class)
                .setParameter("taskId", TaskId)
                .setParameter("username", username)
                .getResultList();
    }
}
