package com.jovakinn.notificationservice.orderhistory.domain.entities;

import com.jovakinn.notificationservice.orderhistory.domain.interfaces.IOrderHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@Table(name = "order_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory extends AbstractEntity implements IOrderHistory {
    @Column(name = "order_number", unique = true)
    public String orderNumber;

    public OrderHistory(IOrderHistory orderHistory) {
        this.orderNumber = orderHistory.getOrderNumber();
    }
}
