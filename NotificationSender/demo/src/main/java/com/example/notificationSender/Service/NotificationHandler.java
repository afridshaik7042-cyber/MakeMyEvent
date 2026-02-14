package com.example.notificationSender.Service;

import com.example.notificationSender.Model.EventJob;
import com.example.notificationSender.Model.VerifySignUp;
import com.example.notificationSender.Repository.NotificationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationHandler {
    @Autowired
    public SendMail sendMail;

    @Autowired
    public NotificationRepository notificationRepository;

    @KafkaListener(
            topics = {"email-topic"},
            groupId = "email-group"
    )
    public void sendMail(EventJob job) {
        if (!this.notificationRepository.existsByNotificationId(job.getUuid())) {
            this.sendMail.email(job.emailId, job.getEventName());
        }
    }

    @KafkaListener(
            topics = {"mailVerify-topic"},
            groupId = "mail-verify-500"
    )
    public void verifyMail(VerifySignUp verifySignUp) throws MessagingException {
        this.sendMail.verifyMail(verifySignUp);
    }
}
