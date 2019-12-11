package com.unicornies.Projekt.services;

import com.unicornies.Projekt.exceptions.ResourceNotFoundException;
import com.unicornies.Projekt.model.User;
import com.unicornies.Projekt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getAllUsers() {
        return (List<User>) usersRepository.findAll();
    }

    public Optional<User> getUserByID(String userID) {
        return usersRepository.findById(userID);
    }

    public User createUser(User user) {
        return usersRepository.save(user);
    }

    public User updateUser(User user, String userID) {
        verifyUserID(userID, "");
        user.setUserID(userID);
        return usersRepository.save(user);
    }

    public void deleteUserId(String userID) {
        usersRepository.deleteById(userID);
    }

    public void verifyUserID(String userID, String message) throws ResourceNotFoundException {
        Optional<User> user = usersRepository.findById(userID);
        if (!user.isPresent())
            throw new ResourceNotFoundException(message);
    }
}
