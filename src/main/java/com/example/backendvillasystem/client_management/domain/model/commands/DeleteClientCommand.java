package com.example.backendvillasystem.client_management.domain.model.commands;

public record DeleteClientCommand(Long clientId) {

    public DeleteClientCommand {
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("Client id cannot be null or less than 1");
        }
    }
}
