package com.monika.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.monika.dto.EmailRequest;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Async
    private void sendEmail(EmailRequest request) {

        log.info("Sending email to {}", request.getTo());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());

        mailSender.send(message);

        log.info("Email successfully sent to {}", request.getTo());
    }

    //Account creation email
    @Override
    public void sendAccountCreationEmail(String toEmail, String username, String tempPassword) {

        EmailRequest request = new EmailRequest();
        request.setTo(toEmail);
        request.setSubject("Your Phantask Account Has Been Created");

        request.setBody("""
                Hello,

                Your account has been successfully created.

                Username: %s
                Temporary Password: %s

                Please re-login and change your password immediately.

                Regards,
                Phantask Team
                """.formatted(username, tempPassword));

        sendEmail(request);
    }

    //sEarly checkout alert
    @Override
    public void sendEarlyCheckoutAlert(
            String managerEmail,
            String employeeName,
            LocalDateTime checkIn,
            LocalDateTime checkOut,
            long workedMinutes) {

        long hours = workedMinutes / 60;
        long minutes = workedMinutes % 60;

        EmailRequest request = new EmailRequest();
        request.setTo(managerEmail);
        request.setSubject("Early Checkout Alert – " + employeeName);

        request.setBody("""
                Hello,

                This is to inform you that %s has checked out early today.

                Check-in Time : %s
                Check-out Time: %s
                Worked Duration: %dh %dm
                Required Duration: 8h

                Please review this attendance entry if required.

                Regards,
                Phantask Team
                """.formatted(
                employeeName,
                checkIn,
                checkOut,
                hours,
                minutes
        ));

        sendEmail(request);
    }

}
