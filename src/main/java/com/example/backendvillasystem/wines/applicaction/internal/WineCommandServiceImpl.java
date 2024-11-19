package com.example.backendvillasystem.wines.applicaction.internal;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.DeleteWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;
import com.example.backendvillasystem.wines.domain.services.WineCommandService;
import com.example.backendvillasystem.wines.infrastructure.persistence.jpa.WineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WineCommandServiceImpl implements WineCommandService {
    private final WineRepository wineRepository;

    public WineCommandServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    /**
     * Handle the create wine command.
     *
     * @param command The create wine command.
     * @return The created wine item.
     * @throws IllegalArgumentException If the wine item already exists.
     * @see CreateWineCommand
     */
    @Override
    public Optional<Wines> handle(CreateWineCommand command) {
        var wineItem = new Wines(command);
        var createdWineItem = wineRepository.save(wineItem);
        return Optional.of(createdWineItem);
    }

    /**
     * Handle the update wine command.
     *
     * @param command The update wine command.
     * @return The updated wine item.
     * @throws IllegalArgumentException If the wine item does not exist.
     * @see UpdateWineCommand
     */
    @Override
    public Optional<Wines> handle(UpdateWineCommand command) {
        var result = wineRepository.findById(command.wineId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Wine item with id %s not found".formatted(command.wineId()));
        }

        var wineToUpdate = result.get();
        try {
            var updatedWine = wineRepository.save(wineToUpdate.updateWine(
                    command.name(), command.description(), command.type(), command.region(), command.country(),
                    command.year(), command.grapes(), command.alcohol(), command.certification(), command.rating(),
                    command.state(), command.producerId(), command.batchId(), command.link()));
            return Optional.of(updatedWine);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating wine item: %s".formatted(e.getMessage()));
        }
    }

    /**
     * Handle the delete wine command.
     *
     * @param command The delete wine command.
     * @throws IllegalArgumentException If the wine item does not exist.
     * @see DeleteWineCommand
     */
    @Override
    public void handle(DeleteWineCommand command) {
        if (!wineRepository.existsById(command.wineId())) {
            throw new IllegalArgumentException("Wine item with id %s not found".formatted(command.wineId()));
        }
        try {
            wineRepository.deleteById(command.wineId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting wine item: %s".formatted(e.getMessage()));
        }
    }
}
