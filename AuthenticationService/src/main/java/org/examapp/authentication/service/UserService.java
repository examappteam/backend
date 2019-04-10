package org.examapp.authentication.service;

import org.examapp.authentication.repository.UserRepository;
import org.examapp.authentication.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserInfo(String username) {
        return userRepository.findOneByUsername(username)
                .map(user -> new UserResponse(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername()))
                .orElseThrow(() -> new EntityNotFoundException("User not found!"))
                ;
    }
}
