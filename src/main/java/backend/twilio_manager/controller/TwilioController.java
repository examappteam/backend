package backend.twilio_manager.controller;

import backend.twilio_manager.models.TokenRequest;
import backend.twilio_manager.services.TwilioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwilioController {

    private TwilioService twilioService;

    public TwilioController(){
        twilioService = new TwilioService();
    }

    @GetMapping("/tokens")
    public String getToken(@RequestBody TokenRequest tokenRequest){
        return twilioService.createToken(tokenRequest.getIdentity(), tokenRequest.getRoomName());
    }
}