package com.example.user.registration.payload;

import com.example.user.registration.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequestPayload {

    @NotNull
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private Role role;

    public SignUpRequestPayload(){

    }
}