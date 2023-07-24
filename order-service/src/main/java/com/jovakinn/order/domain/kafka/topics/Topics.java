package com.jovakinn.order.domain.kafka.topics;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

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
        @Value("${spring.kafka.template.default-topic}")
        public static String NOTIFICATION_TOPIC;
    }
}
