package com.example.backendvillasystem.wines.domain.services;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.DeleteWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;

import java.util.Optional;

/**
 * @name WineCommandService
 * @summary
 * This interface represents the service that handles the wine-related commands.
 */
public interface WineCommandService {
    /**
     * Handle the create wine command.
     * @param command The create wine command.
     * @return The created wine item.
     *
     * @throws IllegalArgumentException If WineApiKey is null or empty.
     * @see CreateWineCommand
     */
    Optional<Wines> handle(CreateWineCommand command);

    /**
     * Handle the update wine command.
     * @param command The update wine command.
     * @return The updated wine item.
     *
     * @throws IllegalArgumentException If WineApiKey is null or empty.
     * @see UpdateWineCommand
     */
    Optional<Wines> handle(UpdateWineCommand command);

    /**
     * Handle the delete wine command.
     * @param command The delete wine command.
     * @throws IllegalArgumentException If WineApiKey is null or empty.
     * @see DeleteWineCommand
     */
    void handle(DeleteWineCommand command);
}
