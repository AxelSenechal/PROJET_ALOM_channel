package com.example.channel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.channel.model.Message;
import com.example.channel.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/channel")
public class ChannelController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/{channelId}")
    public List<Message> getChannel(@PathVariable String channelId) {
        return messageRepository.findByReceiverId(channelId);
    }


    @PostMapping("/send")
    public String sendtoChannel(@RequestBody Message message) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Message response = restTemplate.postForObject("http://localhost:8084/api/messages/send", message, Message.class);
        return "Message sent: " + response.toString();
    }


}
