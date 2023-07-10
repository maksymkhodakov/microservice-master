package com.jovakinn.productservice.domain.data;

import com.jovakinn.productservice.domain.interfaces.IBaseProduct;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements IBaseProduct {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Digits(integer = 6, fraction = 3)
    private BigDecimal price;
}
