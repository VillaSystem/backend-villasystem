package com.example.backendvillasystem.inventory_management.interfaces.rest.resources;

public record UpdateInventoryResource(String name, String type, String unit, String expirationDate,
                                      String supplier, Double unitCost, int quantity) {
    public UpdateInventoryResource {
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
            throw new IllegalArgumentException("Unit cost cannot be null or less than or equal to 0");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
}
