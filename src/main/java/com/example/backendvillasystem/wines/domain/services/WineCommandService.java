package com.example.backendvillasystem.wines.domain.services;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.infrastructure.persistence.jpa.WineRepository;
import org.springframework.stereotype.Service;

@Service
public class WineCommandService {
    private final WineRepository wineRepository;

    public WineCommandService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public Wines createWine(CreateWineCommand command) {
        Wines wine = new Wines(command);
        return wineRepository.save(wine);
    }
}
