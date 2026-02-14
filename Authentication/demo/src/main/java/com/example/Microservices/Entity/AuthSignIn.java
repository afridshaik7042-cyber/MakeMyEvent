package com.example.Microservices.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "authentication"
)
public class AuthSignIn {
    @Id
    public UUID id;
    @Column(
            name = "email",
            nullable = false
    )
    public String email;
    @Column(
            name = "passowrd",
            nullable = false
    )
    public String password;
    @Column(
            name = "role"
    )
    public String role = "USER";
    @Column(
            name = "created_at"
    )
    public LocalDateTime timeStamp;
    @Column(
            name = "is_validated"
    )
    public boolean isValid;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(UUID id) {
        this.role = this.role;
    }

    public boolean getIsvalidated() {
        return this.isValid;
    }

    public void setIsValidated(boolean isValid) {
        this.isValid = isValid;
    }
}
