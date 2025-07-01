package com.example.command_service.api.request;

import com.example.command_service.product.ProductInfo;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

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
