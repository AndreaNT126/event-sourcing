package com.example.command_service.account;

import java.util.UUID;

public sealed interface AccountEvent {

    record AccountRegister (
            String fullName,
            String email,
            String password
    ) implements AccountEvent {
    }

    record AccountUpdate (
            UUID accountId,
            String fullName
    ) implements AccountEvent {
    }

    record AccountChangePassword (
            UUID accountId,
            String oldPassword,
            String newPassword
    ) implements AccountEvent {
    }

    record AccountForgotPassword (
            String email
    ) implements AccountEvent {
    }
}
