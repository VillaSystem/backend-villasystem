package com.example.backendvillasystem.batch_management.domain.model.queries;

public record GetBatchesByIdQuery(Long id) {
    public GetBatchesByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
