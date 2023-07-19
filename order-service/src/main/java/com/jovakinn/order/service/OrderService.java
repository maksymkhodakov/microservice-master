package com.jovakinn.order.service;

import com.jovakinn.order.domain.data.OrderRequest;
import com.jovakinn.order.exceptions.OrderNotInStockException;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest) throws OrderNotInStockException;
}
