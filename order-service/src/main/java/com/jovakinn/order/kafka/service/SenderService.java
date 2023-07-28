package com.jovakinn.order.kafka.service;

public interface SenderService<K, V> {
    void sendMessage(String topic, Integer partition, Long timestamp, K key, V value);
}
