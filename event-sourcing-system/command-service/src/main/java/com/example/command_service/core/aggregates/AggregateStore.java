package com.example.command_service.core.aggregates;

import com.eventstore.dbclient.*;
import com.example.command_service.core.http.ETag;
import com.example.common.serialization.EventSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AggregateStore<Entity extends AbstractAggregate<Event, Id>, Event, Id> {
  private final EventStoreDBClient eventStore;
  private final Function<Id, String> mapToStreamId;
  private final Supplier<Entity> getEmpty;

  public AggregateStore(
    EventStoreDBClient eventStore,
    Function<Id, String> mapToStreamId,
    Supplier<Entity> getEmpty
  ) {

    this.eventStore = eventStore;
    this.mapToStreamId = mapToStreamId;
    this.getEmpty = getEmpty;
  }

  Optional<Entity> get(Id id) {
    var streamId = mapToStreamId.apply(id);

    var events = getEvents(streamId);

    if (events.isEmpty())
      return Optional.empty();

    var current = getEmpty.get();

    for (var event : events.get()) {
      current.when(event);
    }

    return Optional.ofNullable(current);
  }

  public ETag add(Entity entity) {
    return appendEvents(
      entity,
      AppendToStreamOptions.get().expectedRevision(ExpectedRevision.noStream())
    );
  }

  public ETag getAndUpdate(
    Consumer<Entity> handle,
    Id id,
    long expectedRevision
  ) {
    var streamId = mapToStreamId.apply(id);
    var entity = get(id).orElseThrow(
      () -> new RuntimeException("Stream with id %s was not found".formatted(streamId))
    );

    handle.accept(entity);

    return appendEvents(entity, AppendToStreamOptions.get().expectedRevision(expectedRevision));
  }

  private Optional<List<Event>> getEvents(String streamId) {
    ReadResult result;
    try {
      result = eventStore.readStream(streamId,ReadStreamOptions.get()).get();
    } catch (Throwable e) {
      Throwable innerException = e.getCause();

      if (innerException instanceof StreamNotFoundException) {
        return Optional.empty();
      }
      throw new RuntimeException(e);
    }

    var events = result.getEvents().stream()
      .map(EventSerializer::<Event>deserialize)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .toList();

    return Optional.of(events);
  }

  public ETag appendEvents(Entity entity, AppendToStreamOptions appendOptions) {
    var id = entity.id();
    var streamId = mapToStreamId.apply(id);
    var events = Arrays.stream(entity.dequeueUncommittedEvents())
      .map(event -> EventSerializer.serialize(event));

    try {
      var result = eventStore.appendToStream(
        streamId,
        appendOptions,
        events.iterator()
      ).get();

      return ETag.weak(result.getNextExpectedRevision());
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
