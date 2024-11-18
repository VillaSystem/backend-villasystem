package com.example.backendvillasystem.inventory_management.domain.services;

import com.example.backendvillasystem.inventory_management.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory_management.domain.model.queries.GetInventoriesByIdQuery;
import com.example.backendvillasystem.inventory_management.domain.model.queries.GetInventoriesByProducerIdQuery; // Asegúrate de tener este query

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

    /**
     * Handle the get inventories by producer id query
     * @param query The get inventories by producer id query
     * @return List of inventories for the given producer
     *
     * @see GetInventoriesByProducerIdQuery
     */
    List<Inventories> handle(GetInventoriesByProducerIdQuery query);
}
