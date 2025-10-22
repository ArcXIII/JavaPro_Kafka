package org.arcsoft.javapro_kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    void consumeMessage(MessageDto messageDto) {
      log.info("Received Message from Kafka: {}", messageDto);
    }

    @KafkaListener(topics = "javapro-kafka-another")
    void consumeAnotherMessage(AnotherMessageDto messageDto) {
        log.info("Received Message from Kafka: {}", messageDto);
    }
}
