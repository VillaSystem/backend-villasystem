package com.example.backendvillasystem.wines.application.internal;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.services.WineQueryService;
import com.example.backendvillasystem.wines.infrastructure.persistence.jpa.WineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineQueryServiceImpl implements WineQueryService {
    private final WineRepository wineRepository;

    public WineQueryServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public Optional<Wines> getWineById(Long id) {
        return wineRepository.findById(id);
    }

    @Override
    public List<Wines> getAllWines() {
        return wineRepository.findAll();
    }

    @Override
    public List<Wines> getWinesByCountry(String country) {
        return wineRepository.findByCountry(country);
    }
}
