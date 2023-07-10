package com.jovakinn.order.domain.mapper;

import com.jovakinn.order.domain.dto.OrderItemDTO;
import com.jovakinn.order.domain.enitties.OrderItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderItemMapper {
    public OrderItem mapToEntity(OrderItemDTO orderItemDTO) {
        return OrderItem.builder()
                .price(orderItemDTO.getPrice())
                .skuCode(orderItemDTO.getSkuCode())
                .quantity(orderItemDTO.getQuantity())
                .build();
    }
}
