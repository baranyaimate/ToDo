package com.example.todo.Model.Response;

import com.example.todo.Model.Entity.LabelEntity;
import com.example.todo.Model.Entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class TasksResponse extends TaskEntity {

    private List<String> labelname;

    public List<String> getLabel() {
        return labelname;
    }

    public void setLabel(List<String> labelname) {
        this.labelname = labelname;
    }
}
