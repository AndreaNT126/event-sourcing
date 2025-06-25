package com.example.command_service.core.events;

public record EventForwarder(
    KafkaProducerService kafkaProducerService
) implements EventBus {

    @Override
    public <Event> void publish(EventEnvelope<Event> event) {
        kafkaProducerService.sendEventToKafka("update-read-model",event.metadata().getEventId(),event.data());
    }
}
