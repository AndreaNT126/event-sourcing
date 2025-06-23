package com.example.command_service.api.request;


public final class AuthenticationRequest {

    public record Login(
            String email,
            String password
    ) { }

    public record Register(
        String fullName,
        String password,
        String email
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
