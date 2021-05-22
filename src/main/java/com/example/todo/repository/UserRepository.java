package com.example.todo.repository;

import com.example.todo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    Long countByUsernameAndIsActive(String username, Integer isActive);

    Long countByEmailAndIsActive(String email, Integer isActive);

    UserEntity findByEmailAndIsActive(String email, Integer isActive);
}
