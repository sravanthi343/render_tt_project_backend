package com.cms.service;

import com.cms.config.JwtUtil;
import com.cms.dto.*;
import com.cms.model.User;
import com.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email already registered");
        if (userRepository.existsByUserId(req.getUserId()))
            throw new RuntimeException("User ID already taken");

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setUserId(req.getUserId());
        user.setRole(req.getRole());
        userRepository.save(user);

        return buildResponse(user, "Registration successful");
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid User ID or password"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid User ID or password");
        return buildResponse(user, "Login successful");
    }

    private AuthResponse buildResponse(User user, String message) {
        String token = jwtUtil.generateToken(user.getUserId(), user.getRole().name());
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setMessage(message);
        return response;
    }
}
