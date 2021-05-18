package com.example.todo.Model;

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

    @ManyToOne
    @JoinColumn(name = "taskId")
    private TaskEntity task;

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
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "LabelEntity{" +
                "labelId=" + labelId +
                ", name='" + name + '\'' +
                ", task=" + task +
                '}';
    }
}
