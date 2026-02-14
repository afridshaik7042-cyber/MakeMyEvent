package com.example.notificationSender.Service;

import com.example.notificationSender.Model.VerifySignUp;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMail {
    @Autowired
    public JavaMailSender javaMailSender;

    @Async
    public void email(String emailId, String eventTitle) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailId);
        simpleMailMessage.setSubject("Event Registered Mail");
        simpleMailMessage.setText("This messege regarding your +" + eventTitle + " event");
        this.javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void verifyMail(VerifySignUp verifySignUp) throws MessagingException {
        MimeMessage mailMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
        String link = "http://localhost:8081/auth/verify?token=" + verifySignUp.getToken();
        String text = "<div style=\"font-family:Arial;padding:20px\">\n    <h2>Verify your email</h2>\n\n    <p>Hello %s,</p>\n\n    <p>Click the button below to verify your email:</p>\n\n    <a href=\"%s\"\n       style=\"\n       display:inline-block;\n       padding:12px 20px;\n       font-size:16px;\n       color:white;\n       background-color:#28a745;\n       text-decoration:none;\n       border-radius:5px;\">\n       VERIFY EMAIL\n    </a>\n\n    <p>This link expires in 30 minutes.</p>\n\n    <p>If you didn’t create this account,\n    you can safely ignore this email.</p>\n</div>\n".formatted(verifySignUp.getUserId(), link);
        messageHelper.setTo(verifySignUp.getUserId());
        messageHelper.setSubject("Verify your mail");
        messageHelper.setText(text);
        messageHelper.setFrom("afridshaik2407@gmail.com");
        this.javaMailSender.send(mailMessage);
    }
}
