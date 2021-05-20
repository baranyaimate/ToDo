package com.example.todo.core.service;


import com.example.todo.core.entity.CoreEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CoreCRUDService<T extends CoreEntity> {

    @Transactional
    void add(T entity);

    @Transactional
    void remove(T entity);

    List<T> getAll();

    @Transactional
    void update(T entity);

    T findById(Long id);
}
