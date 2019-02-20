package com.example.user.authentication.service;

import com.example.user.authentication.models.UserWrapper;
import com.example.user.authentication.models.authenticationParticipant;
import com.example.user.authentication.repository.AuthData;
import com.example.user.authentication.utility.Password;
import com.example.user.authentication.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

public class AuthService {

    AuthData data;

    public AuthService(){
        data = new AuthData();
    }

    public ResponseEntity loginUser(authenticationParticipant participant){
        String hashedpass;
        try{
            hashedpass = data.getUserPassword(participant.getEmail());
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>("Invalid Username", HttpStatus.UNAUTHORIZED);
        }

        if(Password.checkPassword(participant.getPassword(), hashedpass)){
            RandomString token = new RandomString();
            UserWrapper wrapper = new UserWrapper(token.nextString(), participant.getEmail(), LocalDateTime.now().plusHours(1));
            data.saveToken(wrapper);
            return new ResponseEntity<>(wrapper.getToken(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity verifyToken(String token) {
        try{
            return new ResponseEntity<>(data.getEmailByAuthKey(token), HttpStatus.OK);
        } catch (AuthenticationCredentialsNotFoundException ex){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        } catch (CredentialsExpiredException ex){
            return new ResponseEntity<>("Expired Token Error", HttpStatus.BAD_REQUEST);
        }
    }
}
