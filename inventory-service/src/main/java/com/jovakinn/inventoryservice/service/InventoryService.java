package com.jovakinn.inventoryservice.service;

import com.jovakinn.inventoryservice.domain.DTO.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> isInStock(final List<String> skuCode);
}
