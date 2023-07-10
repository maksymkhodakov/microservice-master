package com.jovakinn.order.domain.enitties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Table(name = "t_order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends AbstractEntity{

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
