package com.cms.dto;

import com.cms.model.User;
import jakarta.validation.constraints.*;

public class RegisterRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Valid email required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotNull(message = "Role is required")
    private User.Role role;

    public RegisterRequest() {}

    public RegisterRequest(String fullName, String email, String password, String userId, User.Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.role = role;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public User.Role getRole() { return role; }
    public void setRole(User.Role role) { this.role = role; }
}
