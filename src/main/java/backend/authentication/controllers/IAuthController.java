package backend.authentication.controllers;


public interface IAuthController {
    @GET
    @Path("/")
    @Produces("application/json")
    Response getUser(@HeaderParam("Authorization") String authkey);

    @POST
    @Path("/login")
    @Produces("application/json")
    Response loginUser(userDTO user);

    @POST
    @Path("/register")
    @Produces("application/json")
    Response registerUser(userDTO user);
}
