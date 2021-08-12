package com.example.restexample.repositories;

import com.example.restexample.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<UserEntity, Long> {
    public Boolean existsByUsername(String username);
    public UserEntity findByUsername(String username);
}
