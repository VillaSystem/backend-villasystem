package com.example.backendvillasystem.inventory.interfaces.rest.transform;

import com.example.backendvillasystem.inventory.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory.interfaces.rest.resources.InventoryResource;

public class InventoryResourceFromEntityAssembler {
    public static InventoryResource toResourceFromEntity(Inventories entity) {
        return new InventoryResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getUnit(),
                entity.getExpirationDate(),
                entity.getSupplier(),
                entity.getUnitCost(),
                entity.getQuantity(),
                entity.getLastUpdated(),
                entity.getProducerId() // New field for producer ID
        );
    }
}
