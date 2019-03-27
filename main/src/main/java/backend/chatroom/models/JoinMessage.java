package backend.chatroom.models;

public class JoinMessage extends Message {
    public JoinMessage() {
        super();
        super.setContent("has joined.");
    }
}
