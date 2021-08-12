package com.example.restexample.services;

import com.example.restexample.entities.TodoEntity;
import com.example.restexample.entities.UserEntity;
import com.example.restexample.exception.TodoNotFoundException;
import com.example.restexample.models.Todo;
import com.example.restexample.repositories.TodosRepo;
import com.example.restexample.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodosService {
    @Autowired
    private TodosRepo todosRepo;
    @Autowired
    private UsersRepo usersRepo;

    public TodoEntity createTodo(Long userId, TodoEntity todo) {
        UserEntity user = usersRepo.findById(userId).get();
        todo.setUser(user);
        return todosRepo.save(todo);
    }

    public Todo updateCompleted(Long id) throws TodoNotFoundException {
        Optional<TodoEntity> todoOp = todosRepo.findById(id);
        nullOrNot(todoOp);

        TodoEntity todo = todoOp.get();
        todo.setCompleted(!todo.getCompleted());
        return todosRepo.save(todo).toModel();
    }

    public Todo updateTodo(Long id, TodoEntity updTodo) throws TodoNotFoundException {
        Optional<TodoEntity> todoOp = todosRepo.findById(id);
        nullOrNot(todoOp);

        TodoEntity todo = todoOp.get();
        todo.setCompleted(updTodo.getCompleted());
        todo.setTitle(updTodo.getTitle());
        return todosRepo.save(todo).toModel();
    }

    private void nullOrNot(Optional<TodoEntity> todo) throws TodoNotFoundException {
        if(todo.isEmpty()) {
            throw new TodoNotFoundException("Todo not found");
        }
    }
}
