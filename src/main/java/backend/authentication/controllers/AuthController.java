package backend.authentication.controllers;

import com.google.gson.Gson;
import backend.authentication.*;

@Path("/auth")
public class AuthController implements IAuthController {
    Gson gson;
    AuthData authData;

    public AuthController() {
        gson = new Gson();
        authData = new AuthData();
    }

    @Override
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getUser(@HeaderParam("Authorization") String authkey){
        User user = authData.getUser(authkey);
        return Response.ok().entity(user).build();
    }

    @Override
    @POST
    @Path("/login")
    @Produces("application/json")
    public Response loginUser(userDTO user){
        String token = authData.loginUser(user.getEmail(), user.getPassword());
        return Response.ok().header("authkey", token).build();
    }

    @Override
    @POST
    @Path("/register")
    @Produces("application/json")
    public Response registerUser(userDTO user){
        authData.registerUser(user.getUser(), user.getPassword());
        return Response.status(201).build();
    }
}
