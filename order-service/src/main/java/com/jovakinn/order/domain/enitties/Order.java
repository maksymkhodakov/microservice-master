package com.jovakinn.order.domain.enitties;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends AbstractEntity {

    private String orderNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;
}
