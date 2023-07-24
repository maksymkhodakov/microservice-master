package com.jovakinn.notificationservice.kafka.topics;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@RequiredArgsConstructor
public enum Topics {

    NOTIFICATION(Constants.NOTIFICATION_TOPIC);

    Topics(String topicName) {
        if (!topicName.equals(this.name())) {
            throw new IllegalArgumentException();
        }
    }

    @UtilityClass
    public static class Constants {
        public static final String NOTIFICATION_TOPIC = "notificationTopic";
    }
}

