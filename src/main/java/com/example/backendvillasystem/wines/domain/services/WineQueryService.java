package com.example.backendvillasystem.wines.domain.services;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.infrastructure.persistence.jpa.WineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WineQueryService {
    private final WineRepository wineRepository;

    public WineQueryService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public Optional<Wines> getWineById(Long id) {
        return wineRepository.findById(id);
    }
}
