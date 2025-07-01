package com.example.command_service.product;

import java.util.UUID;

public sealed interface ProductEvent {
    record ProductEventCreated(
            UUID productId,
            ProductInfo productInfo
    ) implements ProductEvent {
    }

    record ProductEventUpdated(
            ProductInfo productInfo
    ) implements ProductEvent {
    }

    record ProductEventDeleted(
            UUID productId
    ) implements ProductEvent {
    }
}
