package com.example.user.authentication.controllers;

import com.example.user.authentication.models.authenticationParticipant;
import com.example.user.authentication.repository.AuthData;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(
        value = "/auth"
)
public class AuthController {
    Gson gson;
    AuthData authData;

    public AuthController() {
        gson = new Gson();
        authData = new AuthData();
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getUser(@RequestHeader(value = "Authorization") String authkey){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    public ResponseEntity<?> loginUser(authenticationParticipant user){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }


//    @Override
//    @GET
//    @Path("/")
//    @Produces("application/json")
//    public Response getUser(@HeaderParam("Authorization") String authkey){
//        User user = authData.getUser(authkey);
//        return Response.ok().entity(user).build();
//    }
//
//    @Override
//    @POST
//    @Path("/login")
//    @Produces("application/json")
//    public Response loginUser(userDTO user){
//        String token = authData.loginUser(user.getEmail(), user.getPassword());
//        return Response.ok().header("authkey", token).build();
//    }
//
//    @Override
//    @POST
//    @Path("/register")
//    @Produces("application/json")
//    public Response registerUser(userDTO user){
//        authData.registerUser(user.getUser(), user.getPassword());
//        return Response.status(201).build();
//    }
}