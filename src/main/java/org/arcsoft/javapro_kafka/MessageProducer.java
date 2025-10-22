package org.arcsoft.javapro_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    private static final AtomicLong COUNTER = new AtomicLong(0L);
    public static final Random RANDOM = new Random();
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        var messageDto = new MessageDto(StringRandomizer.getRandom(), RANDOM.nextLong());
        log.info("Sending Message to Kafka: {}", messageDto);
        kafkaTemplate.sendDefault(messageDto);
        kafkaTemplate.send("javapro-kafka-another",new AnotherMessageDto("key"+ COUNTER, "value"+ COUNTER));
        COUNTER.addAndGet(1);
    }
}
