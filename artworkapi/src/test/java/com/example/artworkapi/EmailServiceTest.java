package com.example.artworkapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.artworkapi.service.EmailService;

@Component
public class EmailServiceTest implements CommandLineRunner{

    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        // Replace with actual recipient and sender email addresses
        String to = "eallen206@yahoo.com";
        String subject = "Test Email";
        String content = "This is a test email sent from Spring Boot.";
        String from = "mariahacs10@yahoo.com"; // Your Yahoo email address

        try {
            emailService.sendEmail(to, subject, content, from);
            System.out.println("Test email sent successfully.");
        } catch (Exception e) {
            System.err.println("Failed to send test email: " + e.getMessage());
        }
    }
}
