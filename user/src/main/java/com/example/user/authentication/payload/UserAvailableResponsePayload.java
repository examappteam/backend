package com.example.user.authentication.payload;

import lombok.Data;

@Data
public class UserAvailableResponsePayload {
    private boolean available;

    public UserAvailableResponsePayload(Boolean available) { this.available = available; }
}
