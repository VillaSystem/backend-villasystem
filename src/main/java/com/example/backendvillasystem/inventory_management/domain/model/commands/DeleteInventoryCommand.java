package com.example.backendvillasystem.inventory_management.domain.model.commands;

public record DeleteInventoryCommand(Long inventoryId) {

    public DeleteInventoryCommand {
        if (inventoryId == null || inventoryId <= 0) {
            throw new IllegalArgumentException("Inventory ID cannot be null or less than 1");
        }
    }
}