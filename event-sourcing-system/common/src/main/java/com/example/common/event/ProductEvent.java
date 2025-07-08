package com.example.common.event;

import com.example.common.dto.product.ProductInfo;

import java.util.UUID;

public sealed interface ProductEvent {
    record ProductEventCreated(
            UUID productId,
            ProductInfo productInfo
    ) implements ProductEvent {
    }

    record ProductEventUpdated(
            UUID productId,
            ProductInfo productInfo
    ) implements ProductEvent {
    }

    record ProductEventDeleted(
            UUID productId
    ) implements ProductEvent {
    }
}
