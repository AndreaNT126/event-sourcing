package com.example.command_service.category;

import com.eventstore.dbclient.EventStoreDBClient;
import com.example.command_service.core.aggregates.AggregateStore;
import com.example.common.event.CategoryEvent;
import com.example.common.event.ProductEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@Configuration
public class CategoryConfig {

    @Bean
    @ApplicationScope
    AggregateStore<Category, CategoryEvent, UUID> shoppingCartStore(EventStoreDBClient eventStore) {
        return new AggregateStore<>(
                eventStore,
                Category::mapToStreamId,
                Category::empty
        );
    }
}
