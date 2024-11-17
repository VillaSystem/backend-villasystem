package com.example.backendvillasystem.inventory_management.domain.services;

import com.example.backendvillasystem.inventory_management.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory_management.domain.model.commands.CreateInventoryCommand;
import com.example.backendvillasystem.inventory_management.domain.model.commands.DeleteInventoryCommand;
import com.example.backendvillasystem.inventory_management.domain.model.commands.UpdateInventoryCommand;

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

    /**
     * Handle the update inventory command
     * @param command The update inventory command
     * @return The updated inventory item
     *
     * @throws IllegalArgumentException If InventoryApiKey is null or empty
     * @see UpdateInventoryCommand
     */
    Optional<Inventories> handle(UpdateInventoryCommand command);

    /**
     * Handle the delete inventory command
     * @param command The delete inventory command
     * @throws IllegalArgumentException If InventoryApiKey is null or empty
     * @see DeleteInventoryCommand
     */
    void handle(DeleteInventoryCommand command);
}
