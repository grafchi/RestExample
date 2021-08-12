package com.example.restexample.controllers;

import com.example.restexample.entities.TodoEntity;
import com.example.restexample.exception.TodoNotFoundException;
import com.example.restexample.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodosController {
    @Autowired
    private TodosService todosService;

    @PostMapping("/{id}")
    public ResponseEntity createTodo(@RequestBody TodoEntity todo,
                                     @PathVariable(value = "id") Long userId) {
        try {
            todosService.createTodo(userId, todo);
            return ResponseEntity.ok("Todo created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping
    public ResponseEntity updateTodoCompleted(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(todosService.updateCompleted(id));
        } catch (TodoNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTodo(@PathVariable(value = "id") Long id,
                                     @RequestBody TodoEntity todo) {
        try {
            return ResponseEntity.ok(todosService.updateTodo(id, todo));
        } catch (TodoNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
