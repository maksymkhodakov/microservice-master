package com.jovakinn.notificationservice.orderhistory.repository;

import com.jovakinn.notificationservice.orderhistory.domain.entities.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
