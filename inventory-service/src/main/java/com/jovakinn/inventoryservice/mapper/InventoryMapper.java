package com.jovakinn.inventoryservice.mapper;

import com.jovakinn.inventoryservice.domain.DTO.InventoryDTO;
import com.jovakinn.inventoryservice.domain.entities.Inventory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryMapper {
    public InventoryDTO toDTO(Inventory inventory) {
        return InventoryDTO.builder()
                .skuCode(inventory.getSkuCode())
                .inStock(inventory.getQuantity() > 0)
                .build();
    }
}
