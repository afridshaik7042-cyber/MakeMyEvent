package com.example.notificationSender.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "token_store"
)
public class TokenStore {
    @Id
    @Column(
            nullable = false,
            updatable = false
    )
    private UUID id;
    @Column(
            name = "user_id",
            nullable = false,
            length = 500
    )
    private String userId;
    @Column(
            nullable = false,
            length = 1000
    )
    private String token;
    @Column(
            name = "expiry_time"
    )
    private LocalDateTime expiryTime;
    @Column(
            name = "created_at",
            updatable = false
    )
    private LocalDateTime createdAt;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryTime() {
        return this.expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
