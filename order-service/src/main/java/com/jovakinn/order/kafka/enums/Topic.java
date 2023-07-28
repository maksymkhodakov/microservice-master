package com.jovakinn.order.kafka.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Topic {

    NOTIFICATION(TopicName.NOTIFICATION, List.of(TopicKeys.NOTIFICATION_ORDER_PLACE_TOPIC_KEY));

    private final TopicName topicName;
    private final List<TopicKeys> topicKeys;

}
