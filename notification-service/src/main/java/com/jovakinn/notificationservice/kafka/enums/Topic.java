package com.jovakinn.notificationservice.kafka.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Topic {

    NOTIFICATION(TopicName.NOTIFICATION, List.of(TopicGroupId.NOTIFICATION_TOPIC_GROUP_ID));

    private final TopicName topicName;
    private final List<TopicGroupId> topicGroupIds;

    @UtilityClass
    public static class Constants {
        public static final String NOTIFICATION_TOPIC_NAME = "notification";
        public static final String NOTIFICATION_GROUP_ID = "notificationId";
    }
}
