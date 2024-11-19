package com.example.backendvillasystem.client_management.domain.model.queries;

public record GetClientsByIdQuery(Long id) {
    public GetClientsByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
