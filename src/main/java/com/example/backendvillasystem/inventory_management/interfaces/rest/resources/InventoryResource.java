package com.example.backendvillasystem.inventory_management.interfaces.rest.resources;

public record InventoryResource(
        Long id,
        String name,
        String type,
        String unit,
        String expirationDate,
        String supplier,
        Double unitCost,
        int quantity,
        String lastUpdated,
        Long producerId
) {

}
