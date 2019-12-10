package com.unicornies.Projekt.repository;

import com.unicornies.Projekt.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}
