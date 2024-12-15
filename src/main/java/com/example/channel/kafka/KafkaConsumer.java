package com.example.channel.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.channel.model.Message;
import com.example.channel.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumer {

    @Autowired
    private MessageRepository messageRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "conversations", groupId = "channel-group")
    public void consume(String messageJson) throws JsonProcessingException {
        Message message = objectMapper.readValue(messageJson, Message.class);
        messageRepository.save(message);
    }
}
