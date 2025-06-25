package com.example.command_service.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/register")
    public String register() {
        // Registration logic goes here
        return "User registered successfully";
    }

    @PostMapping("/change-password")
    public String changePassword() {
        // Change password logic goes here
        return "Password changed successfully";
    }

    @PostMapping("/update-profile")
    public String updateProfile() {
        // Update profile logic goes here
        return "Profile updated successfully";
    }
}
