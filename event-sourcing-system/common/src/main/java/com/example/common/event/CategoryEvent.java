package com.example.common.event;

import java.util.UUID;

public sealed interface CategoryEvent {
    record CategoryEventCreated(
            UUID categoryId,
            String name
    ) implements CategoryEvent {
    }

    record CategoryEventUpdated(
            UUID categoryId,
            String name
    ) implements CategoryEvent {
    }

    record CategoryEventDeleted(
            UUID categoryId
    ) implements CategoryEvent {
    }
}
