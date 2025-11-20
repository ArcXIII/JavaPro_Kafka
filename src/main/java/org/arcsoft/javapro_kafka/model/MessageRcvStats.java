package org.arcsoft.javapro_kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageRcvStats {
    long message1Count;
    long message2Count;
}
