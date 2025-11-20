package org.arcsoft.javapro_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class JavaProKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaProKafkaApplication.class, args);
    }

    @Bean
    @Profile("kraft")
    public StatChecker statChecker(MessageService messageService) {
        return new StatChecker(messageService);
    }

    @RequiredArgsConstructor
    public static class StatChecker {

        private final MessageService messageService;

        @Scheduled(fixedRate = 1000)
        private void check() {
            log.info("Stats: {}", messageService.getStats());
        }
    }

}
