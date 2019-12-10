package com.unicornies.Projekt.controllers;

import com.unicornies.Projekt.checker.Response;
import com.unicornies.Projekt.model.User;
import com.unicornies.Projekt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/user")
    public ResponseEntity<?> addUser(@RequestBody User user, @PathVariable Long userId) {
        Response rep = new Response();
        User user1 = usersService.createUser(userId, user);


        if (user1 != null) {
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/user")
                .buildAndExpand(user1.getUserID())
                .toUri();
            responseHeaders.setLocation(newPollUri);

            rep.setCode(HttpStatus.CREATED.value());
            rep.setMessage("User has been created!");
            rep.setData(user1);

            return new ResponseEntity<>(rep, responseHeaders, HttpStatus.CREATED);
        }else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("User could not be created. Try again later :(");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }
}
