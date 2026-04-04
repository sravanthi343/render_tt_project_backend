package com.cms.dto;

import com.cms.model.User;

public class AuthResponse {
    private String token;
    private String userId;
    private String fullName;
    private String email;
    private User.Role role;
    private String message;

    public AuthResponse() {}

    public AuthResponse(String token, String userId, String fullName, String email, User.Role role, String message) {
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public User.Role getRole() { return role; }
    public void setRole(User.Role role) { this.role = role; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
