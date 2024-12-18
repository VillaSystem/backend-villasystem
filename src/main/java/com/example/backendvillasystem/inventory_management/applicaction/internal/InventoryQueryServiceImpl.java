package com.example.backendvillasystem.inventory_management.applicaction.internal;

import com.example.backendvillasystem.inventory_management.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory_management.domain.model.queries.GetInventoriesByIdQuery;
import com.example.backendvillasystem.inventory_management.domain.model.queries.GetInventoriesByProducerIdQuery; // Importar el query
import com.example.backendvillasystem.inventory_management.domain.services.InventoryQueryService;
import com.example.backendvillasystem.inventory_management.infrastructure.presistence.jpa.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {
    private final InventoryRepository inventoryRepository;

    public InventoryQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Handles the GetInventoriesByIdQuery query.
     *
     * @param query The get inventories by id query.
     * @return The inventory item if exists.
     * @throws IllegalArgumentException If id is null.
     * @see GetInventoriesByIdQuery
     */
    @Override
    public Optional<Inventories> handle(GetInventoriesByIdQuery query) {
        return inventoryRepository.findById(query.id());
    }

    /**
     * Retrieves all inventory items.
     *
     * @return List of all inventory items.
     */
    @Override
    public List<Inventories> getAllInventories() {
        return inventoryRepository.findAll();
    }

}
