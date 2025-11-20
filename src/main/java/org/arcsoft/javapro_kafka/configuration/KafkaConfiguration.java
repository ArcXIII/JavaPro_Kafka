package org.arcsoft.javapro_kafka.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Bean
    NewTopic myTopic() {
        return new NewTopic(topic, 3, (short) 3);
    }

}
