package com.example.backendvillasystem.inventory.domain.model.queries;

public record GetInventoriesByIdQuery(Long id) {
    public GetInventoriesByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}