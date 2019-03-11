package com.example.user.authentication.repository;

import com.example.user.authentication.models.UserWrapper;
import com.example.user.authentication.utility.RandomString;
import com.example.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

@Component
public class AuthData {

    @Autowired
    private UserService userService;

    protected static final ArrayList<UserWrapper> loggedinusers = new ArrayList<>();
    static RandomString session = new RandomString();
    private static final Logger LOGGER = Logger.getLogger(AuthData.class.getName());

    public String getEmailByAuthKey(String authkey){
        if(loggedinusers.stream().anyMatch(wrapper -> wrapper.getToken().equals(authkey))){
            UserWrapper wrapper = loggedinusers.stream().filter(userwrapper -> userwrapper.getToken().equals(authkey)).findFirst().get();
            if(LocalDateTime.now().isBefore(wrapper.getExpiredTime())){
                wrapper.setExpiredTime(LocalDateTime.now().plusHours(1));
                return wrapper.getUserEmail();
            } else{
                throw new CredentialsExpiredException("Authentication key expired.");
            }
        } else{
            throw new AuthenticationCredentialsNotFoundException("User not logged in.");
        }
    }

    public String getUserPassword(String email) {
        if(userService.userExistsByEmail(email)){
            return userService.getUserWithPassword(email).getPassword();
        } else{
            throw new UsernameNotFoundException("User does not exist.");
        }
    }

    public void saveToken(UserWrapper wrapper) { loggedinusers.add(wrapper); }

    public boolean checkIfLoggedIn(String email) { return loggedinusers.stream().anyMatch(wrapper -> wrapper.getUserEmail().equals(email)); }
}