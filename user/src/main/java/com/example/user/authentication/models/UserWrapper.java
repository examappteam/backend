package com.example.user.authentication.models;

import java.time.LocalDateTime;

public class UserWrapper {
    private String token;
    private String userEmail;
    private LocalDateTime expiredTime;

    public UserWrapper(String token, String userEmail, LocalDateTime expiredTime){
        this.token = token;
        this.userEmail = userEmail;
        this.expiredTime = expiredTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}
