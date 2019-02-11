package backend.twilio_manager.models;

import com.twilio.Twilio;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.rest.api.v2010.account.NewKey;

public class TokenGenerator {

    private final String twilioAccountSid = "ACb35913853c15161369b9bac3e4ff3988";
    private final String twilioAccountAuthToken = "96f3876ea51dace7a37be3f38dac4127";

    private VideoGrant createVideoGrant(String roomName){
        VideoGrant grant = new VideoGrant();
        grant.setRoom(roomName);

        return grant;
    }

    public String createAccessToken(String identity, String roomName){

        Twilio.init(twilioAccountSid, twilioAccountAuthToken);
        NewKey newKey = NewKey.creator().create();

        AccessToken token = new AccessToken.Builder(
                twilioAccountSid,
                newKey.getSid(),
                newKey.getSecret()
        ).identity(identity).grant(createVideoGrant(roomName)).build();

        return token.toJwt();
    }
}