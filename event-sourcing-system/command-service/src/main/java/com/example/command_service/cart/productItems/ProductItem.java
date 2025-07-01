package com.example.command_service.cart.productItems;

import java.util.UUID;

public record ProductItem(
    UUID productId,
    int quantity
) {
    public ProductItem {
        if (quantity <= 0)
        throw new IllegalArgumentException("Quantity has to be a positive number");
    }
}
