package com.fiap.fiapcar.application.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerDTO {
    private String name;
    private String email;
    private UUID id;
    private String password;
    private String username;
    private LocalDateTime createdAt;

    public CustomerDTO(String name, String email, UUID id, String password, String username,  LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
        this.username = username;
        this.createdAt = createdAt;
    }

    public CustomerDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

