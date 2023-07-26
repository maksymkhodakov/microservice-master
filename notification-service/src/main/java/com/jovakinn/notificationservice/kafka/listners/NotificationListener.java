package com.jovakinn.notificationservice.kafka.listners;

import com.jovakinn.notificationservice.kafka.events.OrderPlacedEvent;
import com.jovakinn.notificationservice.kafka.topics.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationListener {
    @KafkaListener(topics = Topics.Constants.NOTIFICATION_GROUP_ID, groupId = Topics.Constants.NOTIFICATION_GROUP_ID, containerFactory = "kafkaListenerObjectContainerFactory")
    public void handleNotification(@Payload OrderPlacedEvent orderPlacedEvent) {
        log.info("Received notification for Order - {}", orderPlacedEvent.getOrderNumber());
    }
}
