package com.monika.dto;
import lombok.Data;

@Data
public class AccountCreatedEvent {
    private String email;
    private String username;
    private String tempPassword;
}
