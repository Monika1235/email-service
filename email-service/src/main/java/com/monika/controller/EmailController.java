package com.monika.controller;

import com.monika.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    //Account Creation Email
    @PostMapping("/account-created")
    public ResponseEntity<String> sendAccountCreationEmail(
            @RequestParam String toEmail,
            @RequestParam String username,
            @RequestParam String tempPassword) {

        emailService.sendAccountCreationEmail(
                toEmail,
                username,
                tempPassword
        );

        return ResponseEntity.ok("Account creation email triggered successfully");
    }

    // 🔹 Early Checkout Alert
    @PostMapping("/early-checkout")
    public ResponseEntity<String> sendEarlyCheckoutAlert(
            @RequestParam String managerEmail,
            @RequestParam String employeeName,
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            @RequestParam long workedMinutes) {

        emailService.sendEarlyCheckoutAlert(
                managerEmail,
                employeeName,
                LocalDateTime.parse(checkIn),
                LocalDateTime.parse(checkOut),
                workedMinutes
        );

        return ResponseEntity.ok("Early checkout email triggered successfully");
    }
}
