package com.jovakinn.order.kafka.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TopicKeys {

    NOTIFICATION_ORDER_PLACE_TOPIC_KEY("order-placed-key");

    private final String topicKey;
}
