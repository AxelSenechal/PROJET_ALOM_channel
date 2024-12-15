package com.example.channel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.channel.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverId(String receiverId);
}
