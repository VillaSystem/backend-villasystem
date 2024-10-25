package com.example.backendvillasystem.inventory_management.domain.model.queries;

public record GetInventoriesByProducerIdQuery(Long producerId) {
    public GetInventoriesByProducerIdQuery {
        if (producerId == null) {
            throw new IllegalArgumentException("Producer ID cannot be null");
        }
    }
}
