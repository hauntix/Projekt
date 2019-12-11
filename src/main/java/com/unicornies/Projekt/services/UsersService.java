package com.unicornies.Projekt.services;

import com.unicornies.Projekt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicornies.Projekt.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getAllUsers(User user) {
        List<User> listOfUsers = new ArrayList<>();
        usersRepository.findAll().forEach(listOfUsers::add);
        return listOfUsers;
    }

    public User createUser(Long userId, User user) {
        usersRepository.save(user);
        return user;
    }

    public User updateUser(User user, Long userId) {
        return usersRepository.save(user);
    }


}
