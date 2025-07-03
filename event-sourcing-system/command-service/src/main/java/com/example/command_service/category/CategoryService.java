package com.example.command_service.category;

import com.example.command_service.core.aggregates.AggregateStore;
import com.example.command_service.core.http.ETag;
import com.example.command_service.price.ProductPriceCalculator;
import com.example.common.dto.ProductInfo;
import com.example.common.event.CategoryEvent;
import com.example.common.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final AggregateStore<Category, CategoryEvent, UUID> store;

    public ETag create(UUID id, String name) {
        return store.add(Category.create(id, name));
    }

    public ETag update(UUID id, String name, Long expectedVersion) {
        return store.getAndUpdate(
                current -> current.update(id,name),
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
