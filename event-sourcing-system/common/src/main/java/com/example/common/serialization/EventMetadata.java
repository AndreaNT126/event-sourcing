package com.example.common.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventMetadata {
    private String eventId;
    private long streamPosition;
    private long logPosition;
    private String eventType;
}
