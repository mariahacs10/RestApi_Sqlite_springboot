package com.example.artworkapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.artworkapi.service.EmailService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("test@dummy.com"); // Dummy email address
        message.setFrom("yuyusoamazing@gmail.com"); // Use your sender email
        message.setSubject("Test Email");
        message.setText("This is a simple test email sent via Mailtrap.");
        
        javaMailSender.send(message);
    }
}
