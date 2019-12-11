package com.unicornies.Projekt.repository;

import com.unicornies.Projekt.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findById(String userID);

    void deleteById(String userID);
}
