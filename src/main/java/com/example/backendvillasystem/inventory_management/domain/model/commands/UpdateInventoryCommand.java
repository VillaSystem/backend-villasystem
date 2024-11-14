package com.example.backendvillasystem.inventory_management.domain.model.commands;

public record UpdateInventoryCommand(Long inventoryId, String name, String type, String unit, String expirationDate,
                                     String supplier, Double unitCost, int quantity, String lastUpdated, Long producerId) {

    public UpdateInventoryCommand {
        if (inventoryId == null || inventoryId <= 0) {
            throw new IllegalArgumentException("Inventory ID cannot be null or less than 1");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or blank");
        }
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Unit cannot be null or blank");
        }
        if (supplier == null || supplier.isBlank()) {
            throw new IllegalArgumentException("Supplier cannot be null or blank");
        }
        if (unitCost == null || unitCost <= 0) {
            throw new IllegalArgumentException("Unit cost cannot be null or less than or equal to 0");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (producerId == null || producerId <= 0) {
            throw new IllegalArgumentException("Producer ID cannot be null or less than 1");
        }
    }
}