package backend.twilio_manager.models;

import lombok.Data;

@Data
public class TokenRequest {

    private String identity;
    private String roomName;

    public TokenRequest(){

    }

    public TokenRequest(String identity, String roomName){
        this.identity = identity;
        this.roomName = roomName;
    }
}