package com.example.command_service.core.config;

import com.example.command_service.core.events.EventBus;
import com.example.command_service.core.events.EventForwarder;
import com.example.command_service.core.events.KafkaProducerService;
import com.example.common.serialization.EventSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
    @Bean
    ObjectMapper defaultJSONMapper() {
        return EventSerializer.mapper;
    }

    @Bean
    EventBus eventBus(KafkaProducerService kafkaProducerService) {
        return new EventForwarder(kafkaProducerService);
    }
}
