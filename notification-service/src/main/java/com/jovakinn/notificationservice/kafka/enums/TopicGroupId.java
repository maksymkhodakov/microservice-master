package com.jovakinn.notificationservice.kafka.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TopicGroupId {

    NOTIFICATION_TOPIC_GROUP_ID("notificationId");

    private final String groupId;
}
