package org.arcsoft.javapro_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JavaProKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaProKafkaApplication.class, args);
    }

}
