package com.example.command_service.product;

import com.example.command_service.core.aggregates.AggregateStore;
import com.example.command_service.core.http.ETag;
import com.example.command_service.price.ProductPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final AggregateStore<Product, ProductEvent, UUID> store;
    private final ProductPriceCalculator productPriceCalculator;

    public ETag create(UUID id, ProductInfo productInfo) {
        return store.add(Product.create(id, productInfo));
    }

    public ETag update(UUID id, ProductInfo productInfo, Long expectedVersion) {
        return store.getAndUpdate(
                current -> current.update(productInfo),
                id,
                expectedVersion
        );
    }

    public ETag delete(UUID id, Long expectedVersion) {
        return store.getAndUpdate(
                current -> current.delete(id),
                id,
                expectedVersion
        );
    }
}
