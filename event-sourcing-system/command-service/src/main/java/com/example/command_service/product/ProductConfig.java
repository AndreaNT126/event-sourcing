package com.example.command_service.product;

import com.eventstore.dbclient.EventStoreDBClient;
import com.example.command_service.core.aggregates.AggregateStore;
import com.example.common.event.ProductEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@Configuration
public class ProductConfig {

    @Bean
    @ApplicationScope
    AggregateStore<Product, ProductEvent, UUID> productStore(EventStoreDBClient eventStore) {
        return new AggregateStore<>(
                eventStore,
                Product::mapToStreamId,
                Product::empty
        );
    }
}
