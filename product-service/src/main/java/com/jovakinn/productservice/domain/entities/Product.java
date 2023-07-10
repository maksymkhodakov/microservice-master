package com.jovakinn.productservice.domain.entities;

import com.jovakinn.productservice.domain.interfaces.IBaseProduct;
import com.jovakinn.productservice.domain.interfaces.IProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "products")
public class Product implements IProduct {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product(IBaseProduct iProduct) {
        this.name = iProduct.getName();
        this.description = iProduct.getDescription();
        this.price = iProduct.getPrice();
    }
}
