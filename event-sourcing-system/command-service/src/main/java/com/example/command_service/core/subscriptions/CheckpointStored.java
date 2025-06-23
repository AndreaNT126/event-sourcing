package com.example.command_service.core.subscriptions;

import java.time.LocalDateTime;

record CheckpointStored(
        String subscriptionId,
        long position,
        LocalDateTime checkpointedAt
) {
}
