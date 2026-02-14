package com.example.notificationSender.Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class VerifySignUp {
    public UUID id;
    public String userId;
    public String token;
    public LocalDateTime expiresAt;

    public UUID getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getToken() {
        return this.token;
    }

    public LocalDateTime getExpiresAt() {
        return this.expiresAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
