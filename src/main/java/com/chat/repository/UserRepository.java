package com.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.User;


public interface  UserRepository extends JpaRepository<User, Integer>{
    
    User findByEmail(String email);

}
