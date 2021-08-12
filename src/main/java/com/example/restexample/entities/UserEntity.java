package com.example.restexample.entities;

import com.example.restexample.models.Todo;
import com.example.restexample.models.User;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TodoEntity> todos;

    public User toModel() {
        List<Todo> todos = this.todos.stream().map(TodoEntity::toModel).collect(Collectors.toList());
        return new User(this.id, this.username, todos);
    }

    public UserEntity() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
