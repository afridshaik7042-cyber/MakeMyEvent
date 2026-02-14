package com.example.Microservices.Service;

import com.example.Microservices.Model.VerifySignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MailVerifyKafka {
    @Autowired
    public KafkaTemplate<String, VerifySignUp> kafkaTemplate;

    public void verifyMail(VerifySignUp verifySignUp) {
        this.kafkaTemplate.send("mailVerify-topic", verifySignUp.getUserId(), verifySignUp);
    }
}
