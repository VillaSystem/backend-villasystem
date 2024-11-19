package com.example.backendvillasystem.wines.domain.services;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;

import java.util.List;
import java.util.Optional;

public interface WineQueryService {
    Optional<Wines> getWineById(Long id);
    List<Wines> getAllWines();
    List<Wines> getWinesByCountry(String country);
}
