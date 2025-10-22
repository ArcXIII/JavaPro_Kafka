package org.arcsoft.javapro_kafka;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class StringRandomizer {

    static final Random RANDOM = new Random();

    String getRandom() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + RANDOM.nextInt() * (rightLimit - leftLimit + 1);
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
