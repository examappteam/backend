package org.examapp.authenticationservice.controller;

import io.swagger.annotations.ApiOperation;
import org.examapp.authenticationservice.messages.JwtResponse;
import org.examapp.authenticationservice.messages.LoginForm;
import org.examapp.authenticationservice.messages.ResponseMessage;
import org.examapp.authenticationservice.messages.SignUpForm;
import org.examapp.authenticationservice.model.Account;
import org.examapp.authenticationservice.model.ROLE_NAME;
import org.examapp.authenticationservice.model.Role;
import org.examapp.authenticationservice.repository.AccountRepository;
import org.examapp.authenticationservice.repository.RoleRepository;
import org.examapp.authenticationservice.utility.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @ApiOperation(
            value = "Sign into the system",
            response = JwtResponse.class
    )
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @ApiOperation(
            value = "Sign up an account",
            response = ResponseMessage.class
    )
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpRequest) {
        if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail admin-> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Account account = new Account(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ROLE_NAME.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "teacher":
                    Role studentRole = roleRepository.findByName(ROLE_NAME.ROLE_TEACHER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(studentRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(ROLE_NAME.ROLE_STUDENT)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        account.setRoles(roles);
        accountRepository.save(account);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
