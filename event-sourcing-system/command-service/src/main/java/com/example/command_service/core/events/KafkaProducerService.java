package com.example.command_service.core.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Phương thức gửi bất kỳ Object nào đến Kafka
    public void sendEventToKafka(String topic, String key, Object event) {
        log.info("Sending event ....." );
        kafkaTemplate.send(topic, key, event);
    }
}
