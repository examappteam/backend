package com.example.user.user.controllers;

import com.example.user.registration.payload.SignUpRequest;
import com.example.user.user.models.User;
import com.example.user.user.payload.UserAvailableResponse;
import com.example.user.user.repository.UserRepository;
import com.example.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/users/exists/{email}")
    public UserAvailableResponse existsByEmail(@PathVariable String email){
        return new UserAvailableResponse(userService.userExistsByEmail(email));
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
        User user = new User(signUpRequest.getEmail(), signUpRequest.getFullName(), signUpRequest.getPassword(), signUpRequest.getRole());

        User result = userService.saveUser(user);

        if(result == null){
            ResponseEntity.ok("User already exists");
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body("User registration successful");
    }
}