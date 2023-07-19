package com.jovakinn.inventoryservice.service.impl;

import com.jovakinn.inventoryservice.domain.DTO.InventoryDTO;
import com.jovakinn.inventoryservice.mapper.InventoryMapper;
import com.jovakinn.inventoryservice.repository.InventoryRepository;
import com.jovakinn.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryDTO> isInStock(final List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(InventoryMapper::toDTO)
                .toList();
    }
}
