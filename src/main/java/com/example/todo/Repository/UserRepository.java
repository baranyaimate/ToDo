package com.example.todo.Repository;

import com.example.todo.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    Long countByUsername(String username);

    Long countByEmail(String email);
}
