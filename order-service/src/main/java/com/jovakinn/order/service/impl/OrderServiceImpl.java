package com.jovakinn.order.service.impl;

import com.jovakinn.order.domain.enitties.Order;
import com.jovakinn.order.domain.mapper.OrderMapper;
import com.jovakinn.order.repository.OrderRepository;
import com.jovakinn.order.service.OrderService;
import com.jovakinn.order.domain.data.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        final Order order = OrderMapper.mapToEntity(orderRequest);
        orderRepository.saveAndFlush(order);
    }
}
