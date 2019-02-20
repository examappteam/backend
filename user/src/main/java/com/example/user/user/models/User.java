package com.example.user.user.models;

import com.example.user.registration.model.Role;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Email
    private String email;

    private String fullName;

    private String password;

    private Role role;

    public User(@Email String email, String fullName, String password, Role role) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }
}