package com.example.restexample.models;

import com.example.restexample.entities.UserEntity;

import java.util.List;

public class User {
    private Long id;
    private String username;

    private List<Todo> todos;

    public User() {
    }

    public User(Long id, String username, List<Todo> todos) {
        this.id = id;
        this.username = username;
        this.todos = todos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
