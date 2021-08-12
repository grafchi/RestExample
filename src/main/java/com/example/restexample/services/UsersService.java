package com.example.restexample.services;

import com.example.restexample.entities.UserEntity;
import com.example.restexample.exception.UserAlreadyExistsException;
import com.example.restexample.exception.UserNotFoundException;
import com.example.restexample.models.User;
import com.example.restexample.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;

    public UserEntity createUser(UserEntity user) throws UserAlreadyExistsException {
        if(usersRepo.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("this username exist");
        }
        return usersRepo.save(user);
    }

    public UserEntity getUserByUsername(String username) throws UserNotFoundException {
        UserEntity user = usersRepo.findByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public User getUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> user = usersRepo.findById(id);
        nullOrNot(user);
        return user.get().toModel();
    }

    public Long deleteUser(Long id) throws UserNotFoundException {
        nullOrNot(usersRepo.findById(id));
        usersRepo.deleteById(id);
        return id;
    }

    private void nullOrNot(Optional<UserEntity> user) throws UserNotFoundException {
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
    }
}
