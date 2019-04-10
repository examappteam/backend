package org.examapp.authentication.service;

import org.examapp.authentication.config.jwt.JwtAuthenticationTokenProvider;
import org.examapp.authentication.model.Role;
import org.examapp.authentication.model.User;
import org.examapp.authentication.repository.UserRepository;
import org.examapp.authentication.model.response.JwtTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtAuthenticationTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, JwtAuthenticationTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtTokenResponse generateJwtToken(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles())
            roles.add(role.getName().name());

        return userRepository.findOneByUsername(username)
                .filter(account -> passwordEncoder.matches(password, account.getPassword()))
                .map(account -> new JwtTokenResponse(jwtTokenProvider.createToken(user.getUsername(), roles)))
                .orElseThrow(() -> new EntityNotFoundException("User not found!"))
                ;
    }
}
