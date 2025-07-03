package com.example.common.serialization;

import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventDataBuilder;
import com.eventstore.dbclient.ResolvedEvent;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public final class EventSerializer {
  private static final Logger logger = LoggerFactory.getLogger(EventSerializer.class);
  public static final ObjectMapper mapper =
    new JsonMapper()
      .registerModule(new JavaTimeModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

  public static EventData serialize(Object event) {
    try {
      return EventDataBuilder.json(
        UUID.randomUUID(),
        EventTypeMapper.toName(event.getClass()),
        mapper.writeValueAsBytes(event)
      ).build();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <Event> Optional<Event> deserialize(ResolvedEvent resolvedEvent) {
    var eventClass = EventTypeMapper.toClass(resolvedEvent.getEvent().getEventType());

    if (eventClass.isEmpty())
      return Optional.empty();

    return deserialize(eventClass.get(), resolvedEvent);
  }

  public static <Event> Optional<Event> deserialize(Class<Event> eventClass, ResolvedEvent resolvedEvent) {
    try {

      var result = mapper.readValue(resolvedEvent.getEvent().getEventData(), eventClass);

      if (result == null)
        return Optional.empty();

      return Optional.of(result);
    } catch (IOException e) {
      logger.warn("Error deserializing event %s".formatted(resolvedEvent.getEvent().getEventType()), e);
      return Optional.empty();
    }
  }

  public static <Event> Optional<Event> deserializeEvent( Object eventData,EventMetadata eventMetadata) {
    var eventClass = EventTypeMapper.toClass(eventMetadata.getEventType());

    if (eventClass.isEmpty())
      return Optional.empty();

    return deserializeEvent(eventClass.get(), eventData);
  }

  public static <Event> Optional<Event> deserializeEvent(Class<Event> eventClass, Object eventData) {
    try {
      var jsonParser = mapper.getFactory().createParser(mapper.writeValueAsString(eventData));
      var result = mapper.readValue(jsonParser, eventClass);

      if (result == null)
        return Optional.empty();

      return Optional.of(result);
    } catch (IOException e) {
      logger.warn("Error deserializing event %s".formatted(eventData.toString()), e);
      return Optional.empty();
    }
  }
}
