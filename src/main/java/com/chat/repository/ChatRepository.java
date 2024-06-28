package com.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.model.Chat;

@Repository
public interface  ChatRepository extends JpaRepository<Chat, Integer>{
    
}
