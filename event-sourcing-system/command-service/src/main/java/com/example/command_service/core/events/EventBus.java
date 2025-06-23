package com.example.command_service.core.events;

public interface EventBus {
  <Event> void publish(EventEnvelope<Event> event);
}
