package com.example.user.user.payload;

import com.example.user.user.models.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GetUserResponsePayload {

    @NotNull
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String role;

    public GetUserResponsePayload(){

    }

    public GetUserResponsePayload(User user){
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
