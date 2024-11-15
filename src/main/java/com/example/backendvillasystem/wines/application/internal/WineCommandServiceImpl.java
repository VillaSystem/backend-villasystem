package com.example.backendvillasystem.wines.application.internal;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;
import com.example.backendvillasystem.wines.domain.services.WineCommandService;
import com.example.backendvillasystem.wines.infrastructure.persistence.jpa.WineRepository;
import org.springframework.stereotype.Service;

@Service
public class WineCommandServiceImpl implements WineCommandService {
    private final WineRepository wineRepository;

    public WineCommandServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public Wines createWine(CreateWineCommand command) {
        Wines wine = new Wines(command);
        return wineRepository.save(wine);
    }

    @Override
    public Wines updateWine(Long id, UpdateWineCommand command) {
        return wineRepository.findById(id).map(wine -> {
            wine.update(command);
            return wineRepository.save(wine);
        }).orElseThrow(() -> new IllegalArgumentException("Wine not found"));
    }

    @Override
    public void deleteWine(Long id) {
        wineRepository.deleteById(id);
    }
}
