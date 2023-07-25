package com.jovakinn.order.domain.kafka.service;


public interface SenderService<T> {
    void sendMessage(String topic, T message);
}
