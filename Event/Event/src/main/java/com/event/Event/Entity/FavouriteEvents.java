package com.event.Event.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "fav_events"
)
public class FavouriteEvents {
    @Id
    public UUID id;
    @Column(
            name = "event_id",
            nullable = false
    )
    public UUID eventId;
    @Column(
            name = "email",
            nullable = false
    )
    public String email;
    @Column(
            name = "created_at",
            nullable = false
    )
    public LocalDateTime createdAt;

    public UUID getEventId() {
        return this.eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
