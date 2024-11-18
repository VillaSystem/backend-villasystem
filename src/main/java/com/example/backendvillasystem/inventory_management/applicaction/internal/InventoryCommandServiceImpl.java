package com.example.backendvillasystem.inventory_management.applicaction.internal;

import com.example.backendvillasystem.inventory_management.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory_management.domain.model.commands.CreateInventoryCommand;
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
}
