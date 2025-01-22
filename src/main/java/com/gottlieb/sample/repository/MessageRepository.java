package com.gottlieb.sample.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {
    
    public String getMessage() {
        return "Mensagem do Repository: Hello, World!";
    }
}
