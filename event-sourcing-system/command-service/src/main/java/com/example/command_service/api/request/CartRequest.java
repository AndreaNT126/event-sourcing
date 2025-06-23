package com.example.command_service.api.request;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public final class CartRequest {
    public record Open(
            @NonNull UUID clientId
    ) {
    }

    @Validated
    public record BookItemRequest(
            @NonNull UUID bookId,
            @NonNull Integer quantity
    ) {
    }

    public record AddProduct(
            @NonNull BookItemRequest bookItem
    ) {
    }
}
