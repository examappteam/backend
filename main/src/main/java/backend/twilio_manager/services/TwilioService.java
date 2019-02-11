package backend.twilio_manager.services;

import backend.twilio_manager.models.TokenGenerator;

public class TwilioService {

    private TokenGenerator tokenGenerator;

    public TwilioService(){
        this.tokenGenerator = new TokenGenerator();
    }

    public String  createToken(String identity, String roomName){
        return tokenGenerator.createAccessToken(identity, roomName);
    }
}