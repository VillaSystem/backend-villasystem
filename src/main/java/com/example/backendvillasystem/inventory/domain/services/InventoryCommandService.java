package com.example.backendvillasystem.inventory.domain.services;

import com.example.backendvillasystem.inventory.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory.domain.model.commands.CreateInventoryCommand;

import java.util.Optional;

/**
 * @name InventoryCommandService
 * @summary
 * This interface represents the service that handles the inventory commands
 */
public interface InventoryCommandService {
    /**
     * Handle the create inventory command
     * @param command The create inventory command
     * @return The created inventory item
     *
     * @throws IllegalArgumentException If InventoryApiKey is null or empty
     * @see CreateInventoryCommand
     */
    Optional<Inventories> handle(CreateInventoryCommand command);
}
