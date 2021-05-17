package com.example.todo.Model;

import com.example.todo.Core.entity.CoreEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TaskEntity extends CoreEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "description")
    private String description;

    @Column(name = "isImportant")
    @Enumerated(EnumType.ORDINAL)
    private isImportant isImportant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public com.example.todo.Model.isImportant getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(com.example.todo.Model.isImportant isImportant) {
        this.isImportant = isImportant;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "name='" + name + '\'' +
                ", user=" + user +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", isImportant=" + isImportant +
                '}';
    }
}

enum isImportant {
    Yes, No;
}
