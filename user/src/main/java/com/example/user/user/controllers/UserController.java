package com.example.user.user.controllers;

import com.example.user.user.payload.GetUserResponsePayload;
import com.example.user.user.payload.SignUpRequestPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.user.user.models.User;
import com.example.user.user.payload.UserAvailableResponsePayload;
import com.example.user.user.repository.UserRepository;
import com.example.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController(
        value = "/users/"
)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(new GetUserResponsePayload(userService.getUser(email)), HttpStatus.OK);
    }

    @GetMapping("exists/{email}")
    public UserAvailableResponsePayload existsByEmail(@PathVariable String email){
        return new UserAvailableResponsePayload(userService.userExistsByEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestPayload signUpRequest){
        User user = new User(signUpRequest.getEmail(), signUpRequest.getFullName(), signUpRequest.getPassword(), signUpRequest.getRole());

        User result = userService.saveUser(user);

        if(result == null){
            ResponseEntity.ok("User already exists");
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body("User registration successful");
    }
}