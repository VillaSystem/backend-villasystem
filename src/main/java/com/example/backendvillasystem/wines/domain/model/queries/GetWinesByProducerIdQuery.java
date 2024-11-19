package com.example.backendvillasystem.wines.domain.model.queries;

public record GetWinesByProducerIdQuery(String producerId) {
    public GetWinesByProducerIdQuery {
        if (producerId == null) {
            throw new IllegalArgumentException("Producer ID cannot be null");
        }
    }
}
