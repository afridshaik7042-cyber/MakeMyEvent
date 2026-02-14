package com.example.notificationService.Service;

import com.example.notificationService.Model.EventJob;
import com.example.notificationService.Model.EventMessage;
import com.example.notificationService.Repository.EventRepository;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Notification {

    @Autowired
    public KafkaTemplate<String, EventJob> eventMessageKafkaTemplate;
    @Autowired
    public EventRepository eventRepository;

    @PostConstruct
    public void init() {
        System.out.println("DEBUG: Notification Service Bean has been created!");
    }

    @KafkaListener(
            topics = {"event-topic"},
            groupId = "new-unique-group-500"
    )
    public void consumeMsg(EventMessage eventMessage) {
        List<String> eventList = new ArrayList();
        String username = this.eventRepository.findCreatedById(eventMessage.getEventId());
        eventList.add(username);

        for(String email : eventList) {
            EventJob job = new EventJob();
            job.setEmailId(email);
            job.setEventName(eventMessage.getEventName());
            job.setEventId(eventMessage.getEventId());
            job.setUuid(UUID.randomUUID());
            this.eventMessageKafkaTemplate.send("email-topic", email, job);
        }

    }
}
