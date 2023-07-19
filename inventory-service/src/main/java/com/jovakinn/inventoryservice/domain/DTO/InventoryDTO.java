package com.jovakinn.inventoryservice.domain.DTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
    private String skuCode;
    private boolean inStock;
}
