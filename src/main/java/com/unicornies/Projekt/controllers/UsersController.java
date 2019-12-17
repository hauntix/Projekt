package com.unicornies.Projekt.controllers;

import com.unicornies.Projekt.checker.Response;
import com.unicornies.Projekt.model.User;
import com.unicornies.Projekt.model.responses.EnhancedResponse;
import com.unicornies.Projekt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers(User user) {
        List<User> allUsers = (List<User>) usersService.getAllUsers(user);
        Response rep = new Response();
        if(!allUsers.isEmpty()) {
            rep.setCode(HttpStatus.OK.value());
            rep.setData(allUsers);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long userId){
        usersService.verifyUserId(userId, "error fetching customer");

        Optional<User> customer = usersService.getUserById(userId);
        return new ResponseEntity<>(
                EnhancedResponse.create(customer, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @PostMapping(value = "/users")
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

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, @PathVariable("id") Long userId) {
        Response rep = new Response();
        usersService.updateUser(user, userId);
        return new ResponseEntity<>(HttpStatus.CREATED, HttpStatus.OK);
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        usersService.verifyUserId(userId, "this id does not exist for Customers");
        usersService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
