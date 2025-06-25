package com.example.command_service.core.config;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import com.example.command_service.api.background.EventStoreDBSubscriptionBackgroundWorker;
import com.example.command_service.core.events.EventBus;
import com.example.command_service.core.subscriptions.EventStoreDBSubscriptionCheckpointRepository;
import com.example.command_service.core.subscriptions.SubscriptionCheckpointRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
class EventStoreDBConfig {
  @Bean
  @Scope("singleton")
  EventStoreDBClient eventStoreDBClient(@Value("${esdb.connectionstring}") String connectionString) {
    try {
      EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(connectionString);

      return EventStoreDBClient.create(settings);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  @Bean
  SubscriptionCheckpointRepository subscriptionCheckpointRepository(EventStoreDBClient eventStore) {
    return new EventStoreDBSubscriptionCheckpointRepository(eventStore);
  }

  @Bean
  EventStoreDBSubscriptionBackgroundWorker eventStoreDBSubscriptionBackgroundWorker(
          EventStoreDBClient eventStore,
          SubscriptionCheckpointRepository subscriptionCheckpointRepository,
          EventBus eventBus
  ) {
    return new EventStoreDBSubscriptionBackgroundWorker(eventStore, subscriptionCheckpointRepository, eventBus);
  }
}
