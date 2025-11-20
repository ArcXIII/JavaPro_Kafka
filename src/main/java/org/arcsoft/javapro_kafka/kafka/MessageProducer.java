package org.arcsoft.javapro_kafka.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arcsoft.javapro_kafka.model.MessageDto;
import org.arcsoft.javapro_kafka.model.MessageDto2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaTemplate<UUID, Object> kafkaTemplate;

    public void sendMessages(List<MessageDto> messages) {
        for (var message : messages) {
            kafkaTemplate.sendDefault(UUID.randomUUID(), message);
        }
    }

    public void sendMessages2(List<MessageDto2> messages) {
        for (var message : messages) {
            kafkaTemplate.sendDefault(UUID.randomUUID(), message);
        }
    }
}
