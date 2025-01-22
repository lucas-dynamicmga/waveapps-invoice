package com.gottlieb.sample.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gottlieb.sample.service.MessageService;
import com.gottlieb.sample.service.dto.MessageDTO;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    
    private final MessageService messageService;

    @Autowired
    public HelloWorldController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/hello-world")
    public MessageDTO sayHello(@RequestParam("userMessage") String userMessage) {
        return messageService.combineMessageWithDTO(userMessage);
    }
}