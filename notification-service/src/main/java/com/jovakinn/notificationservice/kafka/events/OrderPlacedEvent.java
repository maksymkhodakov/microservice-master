package com.jovakinn.notificationservice.kafka.events;

import com.jovakinn.notificationservice.orderhistory.domain.interfaces.IOrderHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent implements IOrderHistory {
    private String orderNumber;
}
