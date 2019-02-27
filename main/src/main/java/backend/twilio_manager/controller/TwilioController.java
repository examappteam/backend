package backend.twilio_manager.controller;

import backend.twilio_manager.services.TwilioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwilioController {

    private TwilioService twilioService;

    public TwilioController(){
        twilioService = new TwilioService();
    }

//    @GetMapping("/tokens")
//    public String getToken(@RequestBody TokenRequest tokenRequest){
//        return twilioService.createToken(tokenRequest.getIdentity(), tokenRequest.getRoomName());
//    }


    @GetMapping("/tokens")
    public String getToken(@RequestParam String identity, @RequestParam String roomName){
        return twilioService.createToken(identity, roomName);
    }
}