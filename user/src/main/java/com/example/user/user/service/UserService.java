package com.example.user.user.service;

import com.example.user.user.models.User;
import com.example.user.user.payload.GetUserResponsePayload;
import com.example.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean userExistsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User saveUser(User user){
        if(userExistsByEmail(user.getEmail())){
            return null;
        }
        return userRepository.save(user);
    }

    public User getUser(String email){
        return userRepository.getUserByEmail(email);
    }
}
