package com.example.artworkapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

// This class is responsible for sending emails in the application
@Service
public class EmailService {

    // Autowired JavaMailSender bean for sending emails
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends an email with the given parameters
     * 
     * @param to      The recipient's email address
     * @param subject The subject of the email
     * @param content The content/body of the email
     * @param from    The sender's email address
     * @throws RuntimeException if there's an error sending the email
     */
    public void sendEmail(String to, String subject, String content, String from) {
        try {
            // Create a new MimeMessage object
            MimeMessage message = mailSender.createMimeMessage();
            
            // Create a helper object to set the email properties
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            // Set the recipient email address
            helper.setTo(to);
            
            // Set the email subject
            helper.setSubject(subject);
            
            // Set the email content (HTML enabled)
            helper.setText(content, true);
            
            // Set the sender email address
            helper.setFrom(from);
            
            // Send the email
            mailSender.send(message);
        } catch (MessagingException e) {
            // If there's an error, throw a RuntimeException with a descriptive message
            throw new RuntimeException("Failed to send email", e);
        }
    }
}