package com.example.user.user.payload;

import lombok.Data;

@Data
public class UserAvailableResponse {
    private boolean available;

    public UserAvailableResponse(Boolean available) { this.available = available; }
}
