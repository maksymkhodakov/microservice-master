package com.jovakinn.order.domain.mapper;

import com.jovakinn.order.domain.data.OrderRequest;
import com.jovakinn.order.domain.enitties.Order;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class OrderMapper {
    public Order mapToEntity(OrderRequest orderRequest) {
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItemList(orderRequest.getOrderItems()
                        .stream()
                        .map(OrderItemMapper::mapToEntity)
                        .toList())
                .build();
    }
}
