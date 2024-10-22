package com.example.backendvillasystem.client_management.domain.services;


import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.domain.model.commands.CreateClientCommand;

import java.util.Optional;

/**
 * @name ClientCommandService
 * @summary
 * This interface represents the service that handles the client commands
 */
public interface ClientCommandService {
    /**
     * Handle the create client command
     * @param command The create client command
     * @return The created client
     *
     * @throws IllegalArgumentException If ClientApiKey is null or empty
     * @see CreateClientCommand
     */
    Optional<Clients> handle(CreateClientCommand command);
}
