package org.examapp.authenticationservice.utility;

import io.jsonwebtoken.*;
import org.examapp.authenticationservice.security.AccountPrinciple;
import org.examapp.authenticationservice.service.AccountDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpiration}")
    private int jwtExpiration;

    @Autowired
    private AccountDetailsServiceImpl accountDetailsService;

    public String generateJwtToken(Authentication authentication) {

        AccountPrinciple accountPrincipal = (AccountPrinciple) authentication.getPrincipal();

        Claims claims = Jwts.claims().setSubject(accountPrincipal.getUsername());
        claims.put("AUTH", accountPrincipal.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject((accountPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Authentication getAuthentication(String token){
        // Call loadByUserName method
        UserDetails userDetails = accountDetailsService.loadUserByUsername(getUserNameFromJwtToken(token));

        // Return the stuff that needs to be held in the security context holder
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
