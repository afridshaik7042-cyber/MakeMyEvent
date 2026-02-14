package com.example.notificationSender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(
        name = "notification"
)
public class NotificationEntity {

    @Id
    public UUID id;

    @Column(
            name = "notification_id",
            nullable = false
    )
    public UUID notificationId;

    public UUID getId() {
        return this.id;
    }

    public UUID getNotificationId() {
        return this.notificationId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNotificationId(UUID notificationId) {
        this.notificationId = notificationId;
    }
}
