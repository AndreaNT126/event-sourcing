package com.example.command_service.account;

import com.eventstore.dbclient.*;
import com.example.command_service.api.request.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EventStoreExam {
    @Autowired
    EventStoreDBClient client;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public String appendStream(String streamName, String eventType, String data) {
        EventData data1 = EventDataBuilder.json(UUID.randomUUID(),eventType,data).build();
        try {
            var event = client.appendToStream(streamName, AppendToStreamOptions.get().expectedRevision(ExpectedRevision.any()), data1).get();
            kafkaTemplate.send("event-topic", streamName, data);
            return "Events appended to stream: " + streamName + " at position " + event.getLogPosition();

        } catch (Exception e) {
            return "Error appending event: " + e.getMessage();
        }
    }

    public String readStream(String streamName) {
        try {
            var result = client.readStream(streamName, ReadStreamOptions.get().fromStart()).get();
            StringBuilder events = new StringBuilder();
            for (ResolvedEvent resolvedEvent : result.getEvents()) {
                events.append("Event: ").append(resolvedEvent.getEvent().getEventType())
                      .append(", Data: ").append(new String(resolvedEvent.getEvent().getEventData()))
                      .append("\n");
            }
            return events.toString();
        } catch (Exception e) {
            return "Error reading stream: " + e.getMessage();
        }
    }

    public void threadCompletableFuture(EventDto request){
        EventData data1 = EventDataBuilder.json(UUID.randomUUID(),request.getEventType(),request.getData()).build();
        CompletableFuture<WriteResult> writeEvent = client.appendToStream(request.getStreamName(), AppendToStreamOptions.get().expectedRevision(ExpectedRevision.any()), data1);

        CompletableFuture<ReadResult> readEvent = writeEvent.thenCompose(result -> {
            log.info("starting future read event.");
            return client.readStream(request.getStreamName(), ReadStreamOptions.get().fromStart());
        });

        CompletableFuture<Void> finalFuture = readEvent.thenAccept(readResult -> {
            log.info("Successfully read stream {} . Events found:", request.getStreamName());
            readResult.getEvents().forEach(recordedEvent -> {
                log.info("  Type: {}, Data: {}", recordedEvent.getEvent().getEventType(), new String(recordedEvent.getEvent().getEventData()));
            });
        });

        finalFuture.exceptionally(ex -> {
            log.error("An error occurred in the async chain: {}", ex.getMessage());
            return null;
        });

        try {
            finalFuture.join(); // Đợi cho toàn bộ chuỗi hoàn thành
            log.info("Async chain completed.");
        } catch (Exception e) {
            System.err.println("Error joining final future: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "event-topic",groupId = "command-service-consumer-group")
    public void listen(String message) {
        System.out.println("Consumed message from Kafka: " + message);
        // Xử lý message ở đây
    }
}
