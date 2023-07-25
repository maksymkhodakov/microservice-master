package com.jovakinn.order.domain.kafka.topics;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@RequiredArgsConstructor
public enum Topics {

    NOTIFICATION(Constants.NOTIFICATION);

    Topics(String topicName) {
        if (!topicName.equals(this.name())) {
            throw new IllegalArgumentException();
        }
    }

    @UtilityClass
    public static class Constants {
        public static final String NOTIFICATION = "notification";
    }
}
