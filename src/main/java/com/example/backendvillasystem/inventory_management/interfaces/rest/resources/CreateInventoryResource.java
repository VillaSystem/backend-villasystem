package com.example.backendvillasystem.inventory_management.interfaces.rest.resources;

public record CreateInventoryResource(
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
    public CreateInventoryResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Unit cannot be null or empty");
        }
        if (supplier == null || supplier.isBlank()) {
            throw new IllegalArgumentException("Supplier cannot be null or empty");
        }
        if (unitCost == null || unitCost <= 0) {
            throw new IllegalArgumentException("UnitCost must be greater than 0");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (expirationDate == null || expirationDate.isBlank()) {
            throw new IllegalArgumentException("Expiration date cannot be null or empty");
        }
        if (lastUpdated == null || lastUpdated.isBlank()) {
            throw new IllegalArgumentException("Last updated cannot be null or empty");
        }
        if (producerId == null) {
            throw new IllegalArgumentException("Producer ID cannot be null");
        }
    }
}
