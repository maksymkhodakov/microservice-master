package com.jovakinn.notificationservice.kafka.listners;

import com.jovakinn.notificationservice.kafka.events.OrderPlacedEvent;
import com.jovakinn.notificationservice.kafka.topics.Topics;
import com.jovakinn.notificationservice.orderhistory.domain.entities.OrderHistory;
import com.jovakinn.notificationservice.orderhistory.serivce.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListener {

    private final OrderHistoryService orderHistoryService;

    @KafkaListener(
            topics = Topics.Constants.NOTIFICATION_GROUP_ID,
            groupId = Topics.Constants.NOTIFICATION_GROUP_ID,
            containerFactory = "kafkaListenerObjectContainerFactory"
    )
    public void handleNotification(@Payload OrderPlacedEvent orderPlacedEvent) {
        log.info("Received notification for Order - {}", orderPlacedEvent.getOrderNumber());
        orderHistoryService.saveOrder(new OrderHistory(orderPlacedEvent));
        log.info("Order with order number - {} is successfully saved", orderPlacedEvent.getOrderNumber());
    }
}
