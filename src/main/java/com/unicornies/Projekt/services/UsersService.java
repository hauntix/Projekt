package com.unicornies.Projekt.services;

import com.unicornies.Projekt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicornies.Projekt.repository.*;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public User createUser(Long userId, User user) {
        usersRepository.save(user);
        return user;
    }


}
