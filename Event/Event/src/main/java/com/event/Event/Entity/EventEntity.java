package com.event.Event.Entity;

import com.event.Event.Model.EventStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "events"
)
public class EventEntity {
    @Id
    public UUID id;
    @Column(
            name = "title",
            nullable = false
    )
    public String title;
    @Column(
            name = "description",
            nullable = false
    )
    public String description;
    @Column(
            name = "cost",
            nullable = false
    )
    public String cost;
    @Column(
            name = "created_by",
            nullable = false
    )
    public String createdBy;
    @Column(
            name = "eventtime",
            nullable = false
    )
    public LocalDateTime eventTime;
    @Column(
            name = "hostname",
            nullable = false
    )
    public String hostName;
    @Column(
            name = "event_status",
            nullable = false
    )
    public EventStatus eventStatus;

    public EventEntity() {
    }

    public EventEntity(UUID id, String title, String description, String cost, String createdBy, LocalDateTime eventTime, String hostName, EventStatus eventStatus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.createdBy = createdBy;
        this.eventTime = eventTime;
        this.hostName = hostName;
        this.eventStatus = eventStatus;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public EventStatus getEventStatus() {
        return this.eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
