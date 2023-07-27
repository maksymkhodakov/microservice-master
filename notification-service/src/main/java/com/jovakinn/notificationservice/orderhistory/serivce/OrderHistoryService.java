package com.jovakinn.notificationservice.orderhistory.serivce;

import com.jovakinn.notificationservice.orderhistory.domain.entities.OrderHistory;

public interface OrderHistoryService {
    void saveOrder(OrderHistory order);
}
