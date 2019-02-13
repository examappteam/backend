package com.example.user.authentication.repository;

import backend.shared.enums.Role;
import backend.shared.models.User;
import com.example.user.authentication.models.UserWrapper;
import com.example.user.authentication.models.authenticationParticipant;
import com.example.user.authentication.utility.Password;
import com.example.user.authentication.utility.RandomString;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthData {

    protected static final ArrayList<UserWrapper> loggedinusers = new ArrayList<>();
    static RandomString session = new RandomString();
    private static final Logger LOGGER = Logger.getLogger(AuthData.class.getName());

    public int getIdByAuthKey(String authkey){
        return loggedinusers.stream().filter(o -> o.getToken().equals(authkey)).findFirst().get().getUserId();
    }

    public authenticationParticipant getUser(int id) {

    }

    public String loginUser(String email, String password) {

    }

}
