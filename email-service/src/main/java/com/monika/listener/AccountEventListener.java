package com.monika.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monika.dto.AccountCreatedEvent;
import com.monika.service.EmailService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountEventListener {

    private final EmailService emailService;

    @RabbitListener(queues = "account.created.queue")
    public void handleAccountCreated(String message) {
    	
    	log.info("Received message: '{}'", message);
        try {
        	
            AccountCreatedEvent event =
                    new ObjectMapper().readValue(message, AccountCreatedEvent.class);

            emailService.sendAccountCreationEmail(
                    event.getEmail(),
                    event.getUsername(),
                    event.getTempPassword()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to process account event", e);
        }
    }
}
