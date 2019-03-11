package com.example.user.registration.controller;

import com.example.user.user.payload.SignUpRequestPayload;
import com.example.user.registration.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequestPayload signUpRequestPayload){
        RegistrationService service = new RegistrationService();
        return service.createUser(signUpRequestPayload);
    }

}