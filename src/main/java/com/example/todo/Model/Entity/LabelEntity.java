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

    public LabelEntity(Long labelId, String name, TaskEntity task) {
        this.labelId = labelId;
        this.name = name;
    }

    public LabelEntity() {
    }

    @Override
    public String toString() {
        return "LabelEntity{" +
                "labelId=" + labelId +
                ", name='" + name + '\'' +
                '}';
    }
}
