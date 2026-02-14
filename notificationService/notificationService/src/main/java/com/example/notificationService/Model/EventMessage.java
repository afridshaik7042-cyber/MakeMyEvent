package com.example.notificationService.Model;

import java.util.UUID;

public class EventMessage {
    private UUID eventId;
    private String eventName;

    public EventMessage() {
    }

    public EventMessage(UUID eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public UUID getEventId() {
        return this.eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
