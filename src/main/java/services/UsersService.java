package services;

import models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void createUser(Long user_id, Users users) {
        usersRepository.save(users);
    }
}
