package com.example.todo.model.entity;

import com.example.todo.core.entity.CoreEntity;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taskId")
    private TaskEntity taskId;

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

    public TaskEntity getTask() {
        return taskId;
    }

    public void setTask(TaskEntity taskId) {
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
