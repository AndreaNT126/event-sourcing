package com.example.command_service.api.request;


public final class AuthenticationRequest {

    public record Register(
        String fullName,
        String email,
        String password
    ) { }

    public record updateProfile(
            String fullName
    ) { }

    public record ChangePassword(
            String oldPassword,
            String newPassword
    ) { }

    public record ForgotPassword(
            String email
    ) { }
}
