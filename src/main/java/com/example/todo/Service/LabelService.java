package com.example.todo.Service;

import com.example.todo.Core.service.CoreCRUDService;
import com.example.todo.Model.LabelEntity;
import org.springframework.stereotype.Service;

@Service
public interface LabelService extends CoreCRUDService<LabelEntity> {
}
