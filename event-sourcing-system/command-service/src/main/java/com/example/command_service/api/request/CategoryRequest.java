package com.example.command_service.api.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CategoryRequest {
    public record CategoryCreated(
            @NotNull String name
    ) {
    }

    public record CategoryUpdated(
            @NotNull UUID productId,
            @NotNull String name
    ) {
    }
}
