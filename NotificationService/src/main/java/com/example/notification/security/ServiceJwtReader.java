package com.example.notification.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class ServiceJwtReader {

    @Value("${jwtSecret}")
    private String jwtSecret;

    /**
     * This will retrieve the username from a token
     *
     */
    public String getUsername(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * This will retrieve the roles from the token,
     * Because the token stores the roles as ArrayList<LinkedHashedMap> there should be done some
     * casting to turn this in a List<String>
     *
     */
    private List<String> getRoles(String token){
        ArrayList<LinkedHashMap> parsed  = (ArrayList<LinkedHashMap>) Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("AUTH");

        List<String> roleList = new ArrayList<String>(parsed.get(0).values());

        return roleList;
    }

    /**
     * This will create a UserDetails object from the token,
     * the userDetails are necessary to create and Authentication object later,
     * this is how spring security works.
     *
     */
    private UserDetails getUserDetails(String token){
        String username = getUsername(token);
        List<String> roles = getRoles(token);
        return new ServiceUserDetails(username, roles.toArray(new String[roles.size()]));
    }

    /**
     * This will create a Authentication object, which spring security uses for Authentication purposes.
     * This object needs a Userdetails object and the authorities of it (authorities = roles)
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = getUserDetails(token);
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
}