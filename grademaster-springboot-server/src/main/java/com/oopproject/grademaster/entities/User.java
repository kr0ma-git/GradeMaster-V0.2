// MODEL FOR THE USER COLUMN IN THE DATABASE

package com.oopproject.grademaster.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // Marks the class as a JPA Entity
@Table(name = "user") // Mapped the class to the user table in the database
public class User {
    @Id // Lets spring boot know that it is the id value to be used ;
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The value generated will be handled by the database (AUTO_INCREMENT)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "username") // The value of the string referenced to the column in the table from the database
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and Setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
