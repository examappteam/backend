package backend.twilio_manager.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer  {


    @Override // STOMP = Simple Text Oriented Messaging Protocol
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This will create the endpoint which the clients can use to connect to the WebSocket.
        // withSockJS is optional and is used to enable a fallback option for older browsers which don't support WebSocket connections
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // All messages whose destination starts with /app will be routed to the message-handing methods
        registry.setApplicationDestinationPrefixes("/app");

        // All messages whose destination starts with /topic will be routed to the message broker
        // The message broker sends to message to each client subscribed to that topic
        registry.enableSimpleBroker("/topic");
    }
}