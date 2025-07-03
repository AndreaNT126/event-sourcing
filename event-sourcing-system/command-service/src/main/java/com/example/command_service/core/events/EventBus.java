package com.example.command_service.core.events;

import com.example.common.serialization.EventEnvelopeDto;

public interface EventBus {
  <Event> void publish(EventEnvelopeDto<Event> event);
}
