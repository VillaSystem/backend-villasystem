package com.example.backendvillasystem.inventory.domain.model.commands;

public record CreateInventoryCommand(
        String name, // renamed from 'nombre'
        String type, // renamed from 'tipo'
        String unit, // renamed from 'unidad'
        String expirationDate, // renamed from 'caducidad'
        String supplier, // renamed from 'proveedor'
        Double unitCost, // renamed from 'costoU'
        int quantity, // renamed from 'cantidad'
        String lastUpdated, // renamed from 'ultimaActualizacion'
        Long producerId // new field for producer ID
) {
    public CreateInventoryCommand {
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
        if (producerId == null) {
            throw new IllegalArgumentException("Producer ID cannot be null");
        }
    }
}
