package com.example.backendvillasystem.wines.domain.services;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.queries.GetWinesByIdQuery;
import com.example.backendvillasystem.wines.domain.model.queries.GetWinesByProducerIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * @name WineQueryService
 * @summary
 * This interface represents the service that handles the wine-related queries.
 */
public interface WineQueryService {

    /**
     * Handle the get wine by id query.
     * @param query The get wine by id query.
     * @return The wine item.
     *
     * @throws IllegalArgumentException If id is null.
     * @see GetWinesByIdQuery
     */
    Optional<Wines> handle(GetWinesByIdQuery query);

    /**
     * Handle the get wines by producer id query.
     * @param query The get wines by producer id query.
     * @return List of wines associated with the producer.
     *
     * @throws IllegalArgumentException If producerId is null.
     * @see GetWinesByProducerIdQuery
     */
    List<Wines> handle(GetWinesByProducerIdQuery query);

    /**
     * Retrieve all wines.
     * @return List of all wine items.
     */
    List<Wines> getAllWines();
}
