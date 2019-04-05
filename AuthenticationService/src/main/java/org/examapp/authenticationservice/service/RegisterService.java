package org.examapp.authenticationservice.service;

import org.examapp.service.authentication.model.ROLE_NAME;
import org.examapp.service.authentication.model.Role;
import org.examapp.service.authentication.model.User;
import org.examapp.service.authentication.repository.RoleRepository;
import org.examapp.service.authentication.repository.UserRepository;
import org.examapp.service.authentication.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>();

        request.getRoles().forEach(role -> {
            switch (role) {
                case "student":
                    Role studentRole = roleRepository.findByName(ROLE_NAME.ROLE_STUDENT)
                            .orElseThrow(() -> new EntityNotFoundException("Student Role not found!"));
                    roles.add(studentRole);
                case "teacher":
                    Role teacherRole = roleRepository.findByName(ROLE_NAME.ROLE_TEACHER)
                            .orElseThrow(() -> new EntityNotFoundException("Teacher Role not found!"));
                    roles.add(teacherRole);
                case "admin":
                    Role adminRole = roleRepository.findByName(ROLE_NAME.ROLE_ADMIN)
                            .orElseThrow(() -> new EntityNotFoundException("Admin Role not found!"));
                    roles.add(adminRole);
                default:
                    Role userRole = roleRepository.findByName(ROLE_NAME.ROLE_STUDENT)
                            .orElseThrow(() -> new EntityNotFoundException("Student Role not found!"));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
    }

    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
