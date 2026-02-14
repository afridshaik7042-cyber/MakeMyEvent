package com.example.Microservices.Model;

public class VerifySignUp {
    public String userId;
    public String token;

    public VerifySignUp(String userId, String token) {
        this.token = token;
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getToken() {
        return this.token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
