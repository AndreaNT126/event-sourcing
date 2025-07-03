package com.example.common.serialization;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEnvelopeDto<Event> {
    private Event data;
    private EventMetadata metadata;
}
