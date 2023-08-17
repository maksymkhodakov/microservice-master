package com.jovakinn.order.service;

import com.jovakinn.order.domain.data.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);

    void createOrder(OrderRequest orderRequest);
}
