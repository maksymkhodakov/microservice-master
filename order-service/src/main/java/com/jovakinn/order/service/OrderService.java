package com.jovakinn.order.service;

import com.jovakinn.order.domain.data.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
