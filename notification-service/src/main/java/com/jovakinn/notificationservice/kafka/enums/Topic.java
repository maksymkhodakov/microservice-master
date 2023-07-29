package com.jovakinn.notificationservice.kafka.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Topic {

    NOTIFICATION(TopicName.NOTIFICATION, List.of(TopicGroupId.NOTIFICATION_TOPIC_GROUP_ID));

    private final TopicName topicName;
    private final List<TopicGroupId> topicGroupIds;
}
