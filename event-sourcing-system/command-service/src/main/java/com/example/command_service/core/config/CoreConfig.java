package com.example.command_service.core.config;

import com.example.command_service.core.events.EventBus;
import com.example.command_service.core.events.EventForwarder;
import com.example.command_service.core.serialization.EventSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CoreConfig {
  @Bean
  ObjectMapper defaultJSONMapper() {
    return EventSerializer.mapper;
  }

  @Bean
  EventBus eventBus(ApplicationEventPublisher applicationEventPublisher) {
    return new EventForwarder(applicationEventPublisher);
  }
}
