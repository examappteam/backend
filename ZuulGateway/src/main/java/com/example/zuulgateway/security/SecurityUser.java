package com.example.zuulgateway.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser {

    String ROLE_PREFIX = "ROLE_";

    String email;
    String token;
    String role;

    public SecurityUser(String email, String token, String role){
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public SecurityUser(){}

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

        return list;
    }
}
