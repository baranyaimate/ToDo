package com.example.todo.Model.Entity;

import com.example.todo.Core.entity.CoreEntity;

import javax.persistence.*;

@Table(name = "label")
@Entity
public class LabelEntity extends CoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "labelId")
    private Long labelId;

    @Column(name = "name")
    private String name;

    @Column(name = "taskId")
    private Long taskId;

    @Override
    public Long getId() {
        return labelId;
    }

    @Override
    public void setId(Long labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "LabelEntity{" +
                "labelId=" + labelId +
                ", name='" + name + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
