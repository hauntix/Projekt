package com.unicornies.Projekt.services;

import com.unicornies.Projekt.exceptions.ResourceNotFoundException;
import com.unicornies.Projekt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicornies.Projekt.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;



    public List<User> getAllUsers(User user) {
        List<User> listOfUsers = new ArrayList<>();
        usersRepository.findAll().forEach(listOfUsers::add);
        return listOfUsers;
    }

    public Optional<User> getUserById(Long userId){
        return usersRepository.findById(userId);

    }

    public User createUser(Long userId, User user) {
        usersRepository.save(user);
        return user;
    }

    public User updateUser(User user, Long userId){
        verifyUserId(userId, "");

        user.setUserID(userId);

        return usersRepository.save(user);
    }

    public void deleteUser(Long userId){
        usersRepository.deleteById(userId);
    }

    public void verifyUserId(Long userId, String message) throws ResourceNotFoundException {
        Optional<User> user = usersRepository.findById(userId);

        if(!user.isPresent())
            throw new ResourceNotFoundException(message);
    }
}