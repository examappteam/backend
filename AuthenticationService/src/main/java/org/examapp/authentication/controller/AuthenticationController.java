package org.examapp.authentication.controller;

import io.swagger.annotations.ApiOperation;
import org.examapp.authentication.request.AuthenticationRequest;
import org.examapp.authentication.request.RegisterRequest;
import org.examapp.authentication.response.JwtTokenResponse;
import org.examapp.authentication.service.AuthenticationService;
import org.examapp.authentication.service.RegisterService;
import org.examapp.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final RegisterService registerService;


    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService, RegisterService registerService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.registerService = registerService;
    }

    @ApiOperation(
            value = "Sign in",
            response = JwtTokenResponse.class
    )
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(
                authenticationService.generateJwtToken(
                        request.getUsername(),
                        request.getPassword()),
                HttpStatus.OK)
                ;
    }

    @ApiOperation(
            value = "Sign up"
    )
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {

        if (registerService.existsEmail(request.getEmail()))
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);

        if (registerService.existsUsername(request.getUsername()))
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);

        registerService.register(request);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserInfo(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
