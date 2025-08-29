package com.fiap.fiapcar.application.model;

public class CustomerDTO {
    private String name;
    private String email;
    private String id;
    private String password;
    private String username;

    public CustomerDTO(String name, String email, String id, String password, String username) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
        this.username = username;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}

