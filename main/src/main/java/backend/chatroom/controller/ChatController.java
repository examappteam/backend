package backend.chatroom.controller;

import backend.chatroom.models.ChatMessage;
import backend.chatroom.models.JoinMessage;
import backend.chatroom.models.LeaveMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/room.{id}.join")
    @SendTo("/topic/room.{id}")
    public JoinMessage joinRoom(@DestinationVariable("id") String id, JoinMessage message) {
        return message;
    }

    @MessageMapping("room.{id}.leave")
    @SendTo("/topic/room.{id}")
    public LeaveMessage leaveRoom(@DestinationVariable("id") String id, LeaveMessage message) {
        return message;
    }

    @MessageMapping("room.{id}.message")
    @SendTo("/topic/room.{id}")
    public ChatMessage chatMessage(@DestinationVariable("id") String id, ChatMessage message) {
        return message;
    }
}
