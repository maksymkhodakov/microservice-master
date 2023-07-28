package com.jovakinn.order.kafka.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TopicName {

    NOTIFICATION("notification");

    private final String value;
}
