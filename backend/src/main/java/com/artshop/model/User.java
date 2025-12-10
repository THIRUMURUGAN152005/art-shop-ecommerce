package com.artshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String role; // "CUSTOMER" or "ARTIST"
    private String bio; // Artist bio/description
    private Long createdAt;
    private Long lastLogin;
    private boolean active;
    
    public User() {
        this.createdAt = System.currentTimeMillis();
        this.active = true;
        this.role = "CUSTOMER"; // Default role
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getCreatedAt() { return createdAt; }
    public void setCreatedAt(Long createdAt) { this.createdAt = createdAt; }
    public Long getLastLogin() { return lastLogin; }
    public void setLastLogin(Long lastLogin) { this.lastLogin = lastLogin; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getFullName() { return firstName + " " + lastName; }
    public boolean isArtist() { return "ARTIST".equals(role); }
    public boolean isCustomer() { return "CUSTOMER".equals(role); }
}