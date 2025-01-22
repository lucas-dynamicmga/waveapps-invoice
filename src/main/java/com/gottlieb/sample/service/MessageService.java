package com.gottlieb.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gottlieb.sample.repository.MessageRepository;
import com.gottlieb.sample.service.dto.MessageDTO;

@Service
public class MessageService {
    
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDTO combineMessageWithDTO(String userMessage) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(1L);
        messageDTO.setMessage(messageRepository.getMessage());
        messageDTO.setUserMessage(userMessage);
        return messageDTO;
    }
}
