package com.jovakinn.notificationservice.kafka.topics;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@AllArgsConstructor
@RequiredArgsConstructor
public enum Topics {

    NOTIFICATION(Constants.NOTIFICATION_TOPIC, Constants.NOTIFICATION_GROUP_ID);


    Topics(String topicName, String groupId) {

    }

    @UtilityClass
    public static class Constants {
        public static final String NOTIFICATION_TOPIC = "notification";
        public static final String NOTIFICATION_GROUP_ID = "notificationId";
    }
}

