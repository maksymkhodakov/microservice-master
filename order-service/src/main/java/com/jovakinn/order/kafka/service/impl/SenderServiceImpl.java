package com.jovakinn.order.kafka.service.impl;

import com.jovakinn.order.kafka.service.SenderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SenderServiceImpl<K, V> implements SenderService<K, V> {

    KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void sendMessage(String topic, Integer partition, Long timestamp, K key, V value) {
        final CompletableFuture<SendResult<K, V>> future = kafkaTemplate.send(topic, partition, timestamp, key, value).completable();
        future.whenComplete((result, ex) -> logResult(value, result, ex));
    }

    private void logResult(V message, SendResult<K, V> result, Throwable ex) {
        if (Objects.isNull(ex)) {
            log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        } else {
            log.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
        }
    }
}
