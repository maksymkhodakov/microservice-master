package com.jovakinn.inventoryservice.repository;

import com.jovakinn.inventoryservice.domain.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE i.skuCode =:skuCode")
    Optional<Inventory> findByCode(String skuCode);
}
