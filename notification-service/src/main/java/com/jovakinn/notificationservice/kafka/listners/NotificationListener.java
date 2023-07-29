package com.jovakinn.notificationservice.kafka.listners;

import com.jovakinn.notificationservice.kafka.events.OrderPlacedEvent;
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

    private static final String TOPIC_NAME = "#{T(com.jovakinn.notificationservice.kafka.enums.Topic).NOTIFICATION.getTopicName()}";

    private static final String GROUP_ID = "#{T(com.jovakinn.notificationservice.kafka.enums.Topic).NOTIFICATION.getTopicGroupIds().get(0).getGroupId()";

    private static final String CONTAINER_FACTORY = "kafkaListenerObjectContainerFactory";

    private final OrderHistoryService orderHistoryService;

    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID, containerFactory = CONTAINER_FACTORY)
    public void handleNotification(@Payload OrderPlacedEvent orderPlacedEvent) {
        log.info("Received notification for Order - {}", orderPlacedEvent.getOrderNumber());
        orderHistoryService.saveOrder(new OrderHistory(orderPlacedEvent));
        log.info("Order with order number - {} is successfully saved", orderPlacedEvent.getOrderNumber());
    }
}
