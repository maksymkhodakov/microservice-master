package com.jovakinn.order.domain.events;

import com.jovakinn.order.domain.enitties.Order;
import com.jovakinn.order.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderEvent {
    private Order order;
    private OrderStatus status;
}
