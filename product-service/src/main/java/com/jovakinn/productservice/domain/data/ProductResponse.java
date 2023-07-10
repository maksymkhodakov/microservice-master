package com.jovakinn.productservice.domain.data;

import com.jovakinn.productservice.domain.interfaces.IProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements IProduct {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductResponse(IProduct iProduct) {
        this.id = iProduct.getId();
        this.name = iProduct.getName();
        this.description = iProduct.getDescription();
        this.price = iProduct.getPrice();
    }
}
