package com.example.command_service.api.request;

import com.example.common.dto.product.ProductInfo;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ProductRequest {

    public record ProductCreated(
            @NotNull ProductInfo productInfo
    ) {
    }

    public record ProductUpdated(
            @NotNull UUID productId,
            @NotNull ProductInfo productInfo
    ) {
    }

}
