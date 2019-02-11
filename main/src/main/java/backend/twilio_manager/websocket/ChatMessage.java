package backend.twilio_manager.websocket;

import lombok.Data;

@Data
public class ChatMessage {

    private MessageType type;
    private String sender;
    private String content;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public ChatMessage() {

    }
}