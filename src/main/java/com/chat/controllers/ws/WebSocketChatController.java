package com.chat.controllers.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Controller
@RequiredArgsConstructor
public class WebSocketChatController {

    public static final String CREATE_CHAT = "/topic/chats.create";

    @MessageMapping(CREATE_CHAT)
    public void setCreateChat(String chatName){
        
    }


}
