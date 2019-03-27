package backend.chatroom.models;

public class LeaveMessage extends Message {
    public LeaveMessage() {
        super();
        super.setContent("has left.");
    }
}
