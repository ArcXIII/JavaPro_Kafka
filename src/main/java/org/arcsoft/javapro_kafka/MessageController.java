package org.arcsoft.javapro_kafka;

import lombok.RequiredArgsConstructor;
import org.arcsoft.javapro_kafka.kafka.MessageProducer;
import org.arcsoft.javapro_kafka.model.MessageDto;
import org.arcsoft.javapro_kafka.model.MessageDto2;
import org.arcsoft.javapro_kafka.model.MessageRcvStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private static final Random RANDOM = new Random();
    private final MessageProducer messageProducer;
    private final MessageService messageService;

    @GetMapping("/send")
    void sendMessages() {
        messageProducer.sendMessages(Stream.generate(() -> new MessageDto(UUID.randomUUID())).limit(1_000_000).toList());
    }

    @GetMapping("/send2")
    void sendMessages2() {
        messageProducer.sendMessages2(Stream.generate(() -> new MessageDto2(RANDOM.nextLong())).limit(1_000_000).toList());
    }

    @GetMapping("/stats")
    MessageRcvStats stats() {
        return messageService.getStats();
    }
}
