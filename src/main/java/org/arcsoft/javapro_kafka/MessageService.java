package org.arcsoft.javapro_kafka;

import lombok.extern.slf4j.Slf4j;
import org.arcsoft.javapro_kafka.model.MessageDto;
import org.arcsoft.javapro_kafka.model.MessageDto2;
import org.arcsoft.javapro_kafka.model.MessageRcvStats;
import org.arcsoft.javapro_kafka.repo.Message1;
import org.arcsoft.javapro_kafka.repo.Message1Repository;
import org.arcsoft.javapro_kafka.repo.Message2;
import org.arcsoft.javapro_kafka.repo.Message2Repository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
public class MessageService {

    private final Message1Repository message1Repository;
    private final Message2Repository message2Repository;

    private final Executor executor = Executors.newWorkStealingPool(33);

    private final Queue<MessageDto> message1Queue = new LinkedBlockingQueue<>(100_000_000);
    private final Queue<MessageDto2> message2Queue = new LinkedBlockingQueue<>(100_000_000);

    public MessageService(Message1Repository message1Repository, Message2Repository message2Repository) {
        this.message1Repository = message1Repository;
        this.message2Repository = message2Repository;
    }

    @Scheduled(fixedDelay = 100)
    private void executeRunner() {
        executor.execute(() -> {
            var batch1 = new ArrayList<Message1>(5000);
            for (int n = 0; n < 5000; n++) {
                if (message1Queue.isEmpty()) break;
                batch1.add(new Message1(message1Queue.poll().uuid()));
            }
            message1Repository.saveAll(batch1);
        });

        executor.execute(() -> {
            var batch2 = new ArrayList<Message2>(5000);
            for (int n = 0; n < 5000; n++) {
                if (message2Queue.isEmpty()) break;
                batch2.add(new Message2(message2Queue.poll().randomNumber()));
            }
            message2Repository.saveAll(batch2);
        });
    }

    public void add(MessageDto messageDto) {
        message1Queue.add(messageDto);
    }

    public void add(MessageDto2 messageDto2) {
        message2Queue.add(messageDto2);
    }

    MessageRcvStats getStats() {
        return new MessageRcvStats(message1Repository.count(), message2Repository.count());
    }
}
