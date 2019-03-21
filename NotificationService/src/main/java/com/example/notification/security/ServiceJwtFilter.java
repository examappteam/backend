package com.example.notification.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ServiceJwtFilter extends OncePerRequestFilter {

    @Autowired
    ServiceJwtReader jwtReader;


    /**
     * This is the filter method used to extract the JWT from the Authorization header and
     * insert the correct data (Role informaion) in the SecurityContextHolder, by doing this we can do
     * role checks on the RestController level.
     *
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromHeader(request);

        if(token != null){
            Authentication authentication = jwtReader.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * This method will extract the token from the header, sometimes a client uses Bearer in front of the token,
     * sometimes not, so it will check on both scenarios and return the JWT as it is, if there header is not null.
     *
     */
    private String getJwtFromHeader(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        } else if(authHeader != null){
            return authHeader;
        }

        return null;
    }
}
