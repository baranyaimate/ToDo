package com.example.todo.Model;

import com.example.todo.Core.entity.CoreEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LabelEntity extends CoreEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private TaskEntity taskId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskEntity getTaskId() {
        return taskId;
    }

    public void setTaskId(TaskEntity taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "LabelEntity{" +
                "name='" + name + '\'' +
                ", taskId=" + taskId +
                '}';
    }
}
