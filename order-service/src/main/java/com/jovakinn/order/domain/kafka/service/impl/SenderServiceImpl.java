package com.jovakinn.order.domain.kafka.service.impl;

import com.jovakinn.order.domain.kafka.service.SenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SenderServiceImpl<T> implements SenderService<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void sendMessage(String topic, T message) {
        final CompletableFuture<SendResult<String, T>> future = kafkaTemplate.send(topic, message).completable();
        future.whenComplete((result, ex) -> logResult(message, result, ex));
    }

    private void logResult(T message, SendResult<String, T> result, Throwable ex) {
        if (Objects.isNull(ex)) {
            log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        } else {
            log.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
        }
    }
}
