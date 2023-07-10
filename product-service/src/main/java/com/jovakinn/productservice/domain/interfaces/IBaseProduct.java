package com.jovakinn.productservice.domain.interfaces;

import java.math.BigDecimal;

public interface IBaseProduct {
    String getName();
    String getDescription();
    BigDecimal getPrice();
}
