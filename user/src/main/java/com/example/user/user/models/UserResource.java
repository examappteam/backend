package com.example.user.user.models;

import com.example.user.user.controllers.UserController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserResource extends ResourceSupport {

    private final User user;

    public UserResource(User user){
        this.user = user;
        final String email = user.getEmail();

        add(linkTo(methodOn(UserController.class).getUserByEmail(email)).withSelfRel());
    }
}
