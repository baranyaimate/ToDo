package com.example.todo.Model.Response;

import java.util.ArrayList;

public class MessageResponse {

    private ArrayList<String> msg;

    public MessageResponse(ArrayList<String> msg) {
        this.msg = msg;
    }

    public ArrayList<String> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<String> msg) {
        this.msg = msg;
    }
}