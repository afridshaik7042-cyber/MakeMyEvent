package com.event.Event.Service;

import com.event.Event.Model.EventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventKafka {
    @Autowired
    public KafkaTemplate<String, EventMessage> kafkaTemplate;

    public void sendEvent(EventMessage eventMessage) {
        this.kafkaTemplate.send("event-topic", eventMessage);
        System.out.println("Event sent to kafka");
    }
}
