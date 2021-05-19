package com.example.todo.Repository;

import com.example.todo.Core.service.CoreCRUDService;
import com.example.todo.Model.Entity.LabelEntity;
import com.example.todo.Model.Entity.TaskEntity;

import java.util.List;

public interface LabelRepository extends CoreCRUDService<LabelEntity> {

    List<String> getLabelsByTask(Long TaskId, String username);
}
