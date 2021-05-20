package com.example.todo.model.response;

import java.util.List;

public class MessageResponse {

    private List<String> message;

    public MessageResponse(List<String> message) {
        this.message = message;
    }

    public List<String> getMsg() {
        return message;
    }

    public void setMsg(List<String> message) {
        this.message = message;
    }
}
