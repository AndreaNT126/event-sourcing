package com.example.command_service.core.events;

import com.example.common.serialization.EventEnvelopeDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record EventForwarder(
    KafkaProducerService kafkaProducerService
) implements EventBus {

    @Override
    public <Event> void publish(EventEnvelopeDto<Event> event) {
        log.info("Forwarding event to Kafka: {}", event);
        kafkaProducerService.sendEventToKafka("update-read-model",event.getMetadata().getEventId(),event);
    }
}
