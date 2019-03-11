package com.example.user.user.controllers;

import com.example.user.user.models.User;
import com.example.user.user.models.UserResource;
import com.example.user.user.payload.GetUserResponsePayload;
import com.example.user.user.payload.SignUpRequestPayload;
import com.example.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody SignUpRequestPayload signUpRequestPayload){
        User user = new User(signUpRequestPayload.getEmail(), signUpRequestPayload.getFullName(), signUpRequestPayload.getPassword(), signUpRequestPayload.getRole());

        ResponseEntity<UserResource> result = userService.create(user);

        if(result == null){
            return ResponseEntity.ok("User already exists");
        } else {
            return result;
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(new GetUserResponsePayload(userService.getUser(email)), HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email){
        //todo implemnt
        return null;
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        //todo implement
        return null;
    }
}