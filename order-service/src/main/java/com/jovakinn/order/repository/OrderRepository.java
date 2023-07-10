package com.jovakinn.order.repository;

import com.jovakinn.order.domain.enitties.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
