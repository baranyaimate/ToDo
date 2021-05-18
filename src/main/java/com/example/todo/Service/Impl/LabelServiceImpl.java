package com.example.todo.Service.Impl;

import com.example.todo.Core.service.impl.CoreCRUDServiceImpl;
import com.example.todo.Model.Entity.LabelEntity;
import com.example.todo.Service.LabelService;

public class LabelServiceImpl extends CoreCRUDServiceImpl<LabelEntity> implements LabelService {

    @Override
    protected void updateCore(LabelEntity persistedEntity, LabelEntity entity) {
        persistedEntity.setName(entity.getName());
        persistedEntity.setTask(entity.getTask());
    }

    @Override
    protected Class<LabelEntity> getManagedClass() {
        return LabelEntity.class;
    }

}
