package com.jovakinn.order.domain.kafka.service;


import org.springframework.kafka.core.KafkaTemplate;

public interface SenderService<T> {
    void sendMessage(String topic, T message, KafkaTemplate<String, T> kafkaTemplate);
}
