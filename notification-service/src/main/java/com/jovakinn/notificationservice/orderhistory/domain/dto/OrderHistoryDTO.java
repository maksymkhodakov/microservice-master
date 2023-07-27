package com.jovakinn.notificationservice.orderhistory.domain.dto;

import com.jovakinn.notificationservice.orderhistory.domain.interfaces.IOrderHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderHistoryDTO implements IOrderHistory {
    public String orderNumber;
}
