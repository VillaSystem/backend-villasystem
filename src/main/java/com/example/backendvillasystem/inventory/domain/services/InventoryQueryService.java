package com.example.backendvillasystem.inventory.domain.services;

import com.example.backendvillasystem.inventory.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory.domain.model.queries.GetInventoriesByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * @name InventoryQueryService
 * @summary
 * This interface represents the service that handles the inventory queries
 */
public interface InventoryQueryService {

    /**
     * Handle the get inventories by id query
     * @param query The get inventories by id query
     * @return The inventory item
     *
     * @throws IllegalArgumentException If id is null
     * @see GetInventoriesByIdQuery
     */
    Optional<Inventories> handle(GetInventoriesByIdQuery query);

    /**
     * Retrieve all inventories
     * @return List of all inventory items
     */
    List<Inventories> getAllInventories();
}
