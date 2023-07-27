package com.jovakinn.notificationservice.orderhistory.serivce.Impl;

import com.jovakinn.notificationservice.orderhistory.domain.entities.OrderHistory;
import com.jovakinn.notificationservice.orderhistory.repository.OrderHistoryRepository;
import com.jovakinn.notificationservice.orderhistory.serivce.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public void saveOrder(OrderHistory order) {
        orderHistoryRepository.saveAndFlush(order);
    }
}
