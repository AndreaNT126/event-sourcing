package com.example.command_service.core.events;

import com.eventstore.dbclient.ResolvedEvent;
import com.example.common.serialization.EventEnvelopeDto;
import com.example.common.serialization.EventMetadata;
import com.example.common.serialization.EventSerializer;

import java.util.Optional;

public record EventEnvelope<Event>(
) {
    public static <Event> Optional<EventEnvelopeDto<Event>> of(final Class<Event> type, ResolvedEvent resolvedEvent) {
        if (type == null)
            return Optional.empty();

        var eventData = EventSerializer.deserialize(type, resolvedEvent);

        if (eventData.isEmpty())
            return Optional.empty();

        return Optional.of(
                new EventEnvelopeDto<>(
                        eventData.get(),
                        new EventMetadata(
                                resolvedEvent.getEvent().getEventId().toString(),
                                resolvedEvent.getEvent().getRevision(),
                                resolvedEvent.getEvent().getPosition().getCommitUnsigned(),
                                resolvedEvent.getEvent().getEventType()
                        )
                )
        );
    }
}
