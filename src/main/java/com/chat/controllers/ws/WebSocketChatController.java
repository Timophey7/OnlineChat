package com.chat.controllers.ws;

import com.chat.dto.ChatDto;
import com.chat.model.Chat;
import com.chat.model.User;
import com.chat.repository.ChatRepository;
import com.chat.repository.UserRepository;
import com.chat.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.security.Security;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class WebSocketChatController {

    ChatService chatService;
    ChatRepository chatRepository;
    UserRepository userRepository;

    public static final String CREATE_CHAT = "/topic/chats.create";
    public static final String ADD_USER_TO_CHAT = "/topic/chats.{chat_id}.user.add";


    public static final String FETCH_CREATE_CHAT_EVENT = "/topic/chats.create.event";
    public static final String FETCH_DELETE_CHAT_EVENT = "/topic/chats.delete.event";

    public static final String SEND_MESSAGE_TO_CHAT = "/topic/chats.{chat_id}.messages.send";
    public static final String SEND_MESSAGE_TO_USER = "/topic/chats.{chat_id}.user.{user_id}.messages.send";

    public static final String FETCH_MESSAGES = "/topic/chats.{chat_id}.messages";
    public static final String FETCH_PERSONAL_MESSAGES = "/topic/chats.{chat_id}.participants.{participant_id}";

    @MessageMapping(CREATE_CHAT)
    public void CreateChat(String chatName){
        chatService.createChat(chatName);
    }

    @SubscribeMapping(FETCH_CREATE_CHAT_EVENT)
    public ChatDto fetchCreateChatEvent(String chatName){
        return null;
    }

    @MessageMapping(ADD_USER_TO_CHAT)
    public void setAddUserToChat(
            @DestinationVariable("chat_id") int chat_id,
            Principal principal
    ) throws Exception {
        chatService.addUserToChat(chat_id,principal);
    }


    @MessageMapping(SEND_MESSAGE_TO_CHAT)
    public void setSendMessageToChat(
            @DestinationVariable("chat_id") String chatId,
            Principal principal
    ){
        chatService.sendMessageToChat(chatId,principal);
    }

}
