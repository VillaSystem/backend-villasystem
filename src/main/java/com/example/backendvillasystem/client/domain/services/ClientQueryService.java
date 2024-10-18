package com.example.backendvillasystem.client.domain.services;

import com.example.backendvillasystem.client.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client.domain.model.queries.GetClientsByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * @name ClientQueryService
 * @summary
 * This interface represents the service that handles the client queries
 */
public interface ClientQueryService {

    /**
     * Handle the get clients by id query
     * @param query The get clients by id query
     * @return The client
     *
     * @throws IllegalArgumentException If id is null
     * @see GetClientsByIdQuery
     */
    Optional<Clients> handle(GetClientsByIdQuery query);

    /**
     * Retrieve all clients
     * @return List of all clients
     */
    List<Clients> getAllClients();
}