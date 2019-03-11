package com.example.user.user.service;

import com.example.user.user.utility.EncryptionManager;
import com.example.user.user.models.User;
import com.example.user.user.models.UserResource;
import com.example.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean userExistsByEmail(String email){
        userRepository.save(hardCodedUser());
        return userRepository.existsByEmail(email);
    }

    public ResponseEntity<UserResource> create(User user){
        user.setPassword(EncryptionManager.hashPassword(user.getPassword()));

        if(userExistsByEmail(user.getEmail())){
            // todo: throw error
            return null;
        }

        User createdUser = userRepository.save(user);

        if(createdUser == null){
            // todo: throw error
            return null;
        }

        UserResource resource = new UserResource(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).body(resource);
    }

    public User getUser(String email){
        User user = userRepository.getUserByEmail(email);
        user.setPassword("");
        return user;
    }

    public User getUserWithPassword(String email){
        return userRepository.getUserByEmail(email);
    }

    private User hardCodedUser(){
        String passwordBeforeHash = "test";
        String passwordHashed = EncryptionManager.hashPassword(passwordBeforeHash);
        return new User("test@test.com", "Eindhoven Oulu", passwordHashed, "STUDENT");
    }
}
