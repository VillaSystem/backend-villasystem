package com.example.backendvillasystem.client_management.domain.services;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.domain.model.queries.GetClientsByIdQuery;
import com.example.backendvillasystem.client_management.domain.model.queries.GetClientsByRoleQuery;

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

    /**
     * Handle the get clients by role query
     * @param query The get clients by role query
     * @return List of clients with the given role
     *
     * @see GetClientsByRoleQuery
     */
    List<Clients> handle(GetClientsByRoleQuery query);
}