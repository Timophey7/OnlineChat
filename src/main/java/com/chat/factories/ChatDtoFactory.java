package com.chat.factories;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.chat.dto.ChatDto;
import com.chat.model.Chat;

@Component
public class ChatDtoFactory {
    public ChatDto makeChatDto(Chat chat) {
        return ChatDto.builder()
                .id(chat.getId())
                .name(chat.getName())
                .messages(chat.getMessages())
                .users(chat.getUsers())
                .build();
    }
}
