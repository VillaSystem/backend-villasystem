package com.example.backendvillasystem.inventory.interfaces.rest.resources;

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
