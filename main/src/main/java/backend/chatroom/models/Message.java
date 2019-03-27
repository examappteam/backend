package backend.chatroom.models;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public abstract class Message {
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Date date = new Date();
    private String content;
    private String sender;
    private String time = dateFormat.format(date);

    @Override
    public String toString() {
        return time + ": " + sender + ": " + content;
    }
}
