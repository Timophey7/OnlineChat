package com.chat.service;

import com.chat.controllers.ws.WebSocketChatController;
import com.chat.factories.ChatDtoFactory;
import com.chat.model.Chat;
import com.chat.model.User;
import com.chat.repository.ChatRepository;
import com.chat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ChatService {

    UserRepository userRepository;

    ChatDtoFactory chatDtoFactory;

    ChatRepository chatRepository;

    SimpMessagingTemplate messagingTemplate;

    public void createChat(String chatName) {


        Chat chat = new Chat();
        chat.setName(chatName);
        chat.setChatCode(UUID.randomUUID().toString());

        chatRepository.save(chat);


        messagingTemplate.convertAndSend(
                WebSocketChatController.FETCH_CREATE_CHAT_EVENT,
                chatDtoFactory.makeChatDto(chat)
        );
    }

    public void addUserToChat(int chat_id, Principal principal) throws Exception {
        Chat chat = chatRepository.findById(chat_id).orElse(null);
        if (chat == null){
            throw new Exception();
        }else {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);
            user.addChats(chat);
            chat.addUser(user);
            userRepository.save(user);
            chatRepository.save(chat);
        }
    }

    public void sendMessageToChat(int chatId,Principal principal){
        chatRepository.findById(chatId).orElseThrow();
    }


}
