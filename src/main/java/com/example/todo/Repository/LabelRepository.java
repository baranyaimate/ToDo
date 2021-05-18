package com.example.todo.Repository;

import com.example.todo.Core.service.CoreCRUDService;
import com.example.todo.Model.Entity.LabelEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends CoreCRUDService<LabelEntity> {

}
