package com.example.user.authentication.service;

import com.example.user.authentication.models.UserWrapper;
import com.example.user.authentication.models.authenticationParticipant;
import com.example.user.authentication.repository.AuthData;
import com.example.user.authentication.utility.Password;
import com.example.user.authentication.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    AuthData data;

    public AuthService(){

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

    public String verifyToken(String token) {
        try{
            return data.getEmailByAuthKey(token);
        } catch (AuthenticationCredentialsNotFoundException ex){
            return "Unauthorized";
//            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        } catch (CredentialsExpiredException ex){
            return "Expired Token Error";
//            return new ResponseEntity<>("Expired Token Error", HttpStatus.BAD_REQUEST);
        }
    }
}
