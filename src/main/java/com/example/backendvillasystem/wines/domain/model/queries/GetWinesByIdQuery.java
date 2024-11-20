package com.example.backendvillasystem.wines.domain.model.queries;

public record GetWinesByIdQuery(Long id) {
    public GetWinesByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
