package com.unicornies.Projekt.controllers;

import com.unicornies.Projekt.model.User;
import com.unicornies.Projekt.model.responses.EnhancedResponse;
import com.unicornies.Projekt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = usersService.getAllUsers();
        return new ResponseEntity<>(
                EnhancedResponse.create(allUsers, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<?> getUserId(@PathVariable String userID){
        usersService.verifyUserID(userID, "error fetching user");
        Optional<User> user = usersService.getUserByID(userID);
        return new ResponseEntity<>(
                EnhancedResponse.create(user, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }





}
