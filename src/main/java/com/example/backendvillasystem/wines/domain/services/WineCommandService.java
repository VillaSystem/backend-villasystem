package com.example.backendvillasystem.wines.domain.services;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;

public interface WineCommandService {
    Wines createWine(CreateWineCommand command);
    Wines updateWine(Long id, UpdateWineCommand command);
    void deleteWine(Long id);
}
