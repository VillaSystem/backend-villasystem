package com.example.backendvillasystem.orders_management.domain.model.queries;

public record GetOrderByIdQuery(Long orderId) {
    public GetOrderByIdQuery {
        if (orderId == null) {
            throw new IllegalArgumentException("Order number cannot be null or blank");
        }
    }
}
