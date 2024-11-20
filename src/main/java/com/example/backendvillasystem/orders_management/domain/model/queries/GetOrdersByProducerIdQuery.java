package com.example.backendvillasystem.orders_management.domain.model.queries;

public record GetOrdersByProducerIdQuery(Long producerId) {
    public GetOrdersByProducerIdQuery {
        if (producerId == null) {
            throw new IllegalArgumentException("Producer ID cannot be null or blank");
        }
    }
}
