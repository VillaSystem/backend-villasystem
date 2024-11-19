package com.example.backendvillasystem.batch_management.domain.model.queries;

public record GetBatchesByProducerIdQuery(Long producerId) {
    public GetBatchesByProducerIdQuery {
        if (producerId == null) {
            throw new IllegalArgumentException("producer Id cannot be null");
        }
    }
}
