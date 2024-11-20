package com.example.backendvillasystem.wines.applicaction.internal;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.queries.GetWinesByIdQuery;
import com.example.backendvillasystem.wines.domain.model.queries.GetWinesByProducerIdQuery; // Import the query
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

    /**
     * Handles the GetWinesByIdQuery query.
     *
     * @param query The get wines by id query.
     * @return The wine item if exists.
     * @throws IllegalArgumentException If id is null.
     * @see GetWinesByIdQuery
     */
    @Override
    public Optional<Wines> handle(GetWinesByIdQuery query) {
        return wineRepository.findById(query.id());
    }

    @Override
    public List<Wines> handle(GetWinesByProducerIdQuery query) {
        return wineRepository.findByProducerId(query.producerId());
    }

    /**
     * Retrieves all wine items.
     *
     * @return List of all wine items.
     */
    @Override
    public List<Wines> getAllWines() {
        return wineRepository.findAll();
    }
}
