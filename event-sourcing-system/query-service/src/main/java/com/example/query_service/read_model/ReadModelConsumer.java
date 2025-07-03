package com.example.query_service.read_model;

import com.example.common.event.ProductEvent;
import com.example.common.serialization.EventEnvelopeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.common.serialization.EventSerializer.deserializeEvent;

@Component
@Slf4j
public class ReadModelConsumer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ProductWriteService productWriteService;

    @KafkaListener(topics = "update-read-model", groupId = "read-model-consumer-group")
    public void listen(EventEnvelopeDto<?> event){
        System.out.println("Received event for read model update:" + event.getData());

        var data = deserializeEvent(event.getData(),event.getMetadata());

        if (data.isEmpty() || data.get().getClass().getName().isBlank()) {
            System.out.println("Received empty or invalid event data, skipping processing.");
            return;
        }
        switch (data.get()) {
            case ProductEvent e -> processProductEvent(e);
            default -> System.out.println("Received unsupported event type: " + data.get().getClass().getSimpleName());
        }

    }

    private void processProductEvent(ProductEvent data) {
        if (data instanceof ProductEvent.ProductEventCreated created) {
            productWriteService.save(created);
        } else if (data instanceof ProductEvent.ProductEventUpdated updated) {

            productWriteService.update(updated);
        } else if (data instanceof ProductEvent.ProductEventDeleted deleted) {

            productWriteService.delete(deleted);
        } else {
            System.out.println("Received unsupported ProductEvent type: " + data.getClass().getSimpleName());
        }
    }
}
