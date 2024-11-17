package com.example.backendvillasystem.inventory_management.applicaction.internal;

import com.example.backendvillasystem.inventory_management.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory_management.domain.model.commands.CreateInventoryCommand;
import com.example.backendvillasystem.inventory_management.domain.model.commands.DeleteInventoryCommand;
import com.example.backendvillasystem.inventory_management.domain.model.commands.UpdateInventoryCommand;
import com.example.backendvillasystem.inventory_management.domain.services.InventoryCommandService;
import com.example.backendvillasystem.inventory_management.infrastructure.presistence.jpa.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {
    private final InventoryRepository inventoryRepository;

    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Handle the create inventory command
     *
     * @param command The create inventory command
     * @return The created inventory item
     * @throws IllegalArgumentException If the inventory item already exists
     * @see CreateInventoryCommand
     */
    @Override
    public Optional<Inventories> handle(CreateInventoryCommand command) {
        var inventoryItem = new Inventories(command);
        var createdInventoryItem = inventoryRepository.save(inventoryItem);
        return Optional.of(createdInventoryItem);
    }

    /**
     * Handle the update inventory command
     *
     * @param command The update inventory command
     * @return The updated inventory item
     * @throws IllegalArgumentException If the inventory item does not exist
     * @see UpdateInventoryCommand
     */
    @Override
    public Optional<Inventories> handle(UpdateInventoryCommand command) {
        var result = inventoryRepository.findById(command.inventoryId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Inventory item with id %s not found".formatted(command.inventoryId()));
        }

        var inventoryToUpdate = result.get();
        try {
            var updatedInventory = inventoryRepository.save(inventoryToUpdate.updateInventory(
                    command.name(), command.type(), command.unit(), command.expirationDate(), command.supplier(),
                    command.unitCost(), command.quantity()));
            return Optional.of(updatedInventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating inventory item: %s".formatted(e.getMessage()));
        }
    }

    /**
     * Handle the delete inventory command
     *
     * @param command The delete inventory command
     * @throws IllegalArgumentException If the inventory item does not exist
     * @see DeleteInventoryCommand
     */
    @Override
    public void handle(DeleteInventoryCommand command) {
        if (!inventoryRepository.existsById(command.inventoryId())) {
            throw new IllegalArgumentException("Inventory item with id %s not found".formatted(command.inventoryId()));
        }
        try {
            inventoryRepository.deleteById(command.inventoryId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting inventory item: %s".formatted(e.getMessage()));
        }
    }
}
