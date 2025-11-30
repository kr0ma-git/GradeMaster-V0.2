package org.grademaster.models;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String role;
    private String created_at;

    public User(int id, String name, String username, String email, String role, String created_at) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreated_at() {
        return created_at;
    }
}
