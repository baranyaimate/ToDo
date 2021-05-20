package com.example.todo.repository.impl;

import com.example.todo.core.service.impl.CoreCRUDServiceImpl;
import com.example.todo.model.entity.LabelEntity;
import com.example.todo.repository.LabelRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LabelRepositoryImpl extends CoreCRUDServiceImpl<LabelEntity> implements LabelRepository {

    @Override
    protected void updateCore(LabelEntity persistedEntity, LabelEntity entity) {
        persistedEntity.setName(entity.getName());
    }

    @Override
    protected Class<LabelEntity> getManagedClass() {
        return LabelEntity.class;
    }
}
