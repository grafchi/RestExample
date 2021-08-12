package com.example.restexample.repositories;

import com.example.restexample.entities.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodosRepo extends CrudRepository<TodoEntity, Long> {
}
