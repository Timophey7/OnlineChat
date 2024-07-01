package com.chat.factories;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.chat.dto.ChatDto;
import com.chat.model.Chat;

@Component
public class ChatDtoFactory {
    public ChatDto makeChatDto(Chat chat) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        chatDto.setName(chat.getName());
        chatDto.setChatCode(chat.getChatCode());
        chatDto.setUsers(chat.getUsers());
        chatDto.setMessages(chat.getMessages());
        return chatDto;
    }
}
