package com.example.backendvillasystem.client_management.domain.model.queries;

public record GetClientsByRoleQuery(String role) {
    public GetClientsByRoleQuery {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }
}
