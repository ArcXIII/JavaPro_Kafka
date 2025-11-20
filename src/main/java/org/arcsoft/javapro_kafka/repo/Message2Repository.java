package org.arcsoft.javapro_kafka.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Message2Repository extends CrudRepository<Message2, Long> {
}
