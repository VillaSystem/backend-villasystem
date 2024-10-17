package com.example.backendvillasystem.client.domain.model.queries;

public record GetClientsByIdQuery(Long id) {
    public GetClientsByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
