package com.example.user.registration.service;

import com.example.user.user.payload.SignUpRequestPayload;
import com.example.user.registration.utility.EncryptionManager;
import com.example.user.user.controllers.UserController;
import com.example.user.user.payload.UserAvailableResponsePayload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class RegistrationService {

    public ResponseEntity<?> createUser(SignUpRequestPayload signUpRequestPayload){
        signUpRequestPayload.setPassword(EncryptionManager.hashPassword(signUpRequestPayload.getPassword()));

        // Check if the user already exists
        UserController controller = new UserController();
        UserAvailableResponsePayload response = controller.existsByEmail(signUpRequestPayload.getEmail());

        // Create the user if not
        if(response.isAvailable()){
            return controller.registerUser(signUpRequestPayload);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand("").toUri();

        return ResponseEntity.created(location).body("User already exists");
    }
}