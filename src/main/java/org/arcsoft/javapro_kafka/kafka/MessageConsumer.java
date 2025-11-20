package org.arcsoft.javapro_kafka.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arcsoft.javapro_kafka.MessageService;
import org.arcsoft.javapro_kafka.model.MessageDto;
import org.arcsoft.javapro_kafka.model.MessageDto2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.template.default-topic}")
public class MessageConsumer {

    private final MessageService messageService;

    @KafkaHandler
    void consumeMessageDto(@Payload MessageDto payload) {
       messageService.add(payload);
    }

    @KafkaHandler
    void consumeMessageDto2(@Payload MessageDto2 payload) {
        messageService.add(payload);
    }
}
