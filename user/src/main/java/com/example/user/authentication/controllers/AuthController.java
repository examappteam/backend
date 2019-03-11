package com.example.user.authentication.controllers;

import com.example.user.authentication.models.authenticationParticipant;
import com.example.user.authentication.service.AuthService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    Gson gson;
    @Autowired
    AuthService service;

    public AuthController() {
        gson = new Gson();
    }

    @GetMapping("/auth/verify")
    public String verifyToken(@RequestHeader(value = "Authorization") String authkey){
        return service.verifyToken(authkey);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody authenticationParticipant user){
        return service.loginUser(user);
    }
}