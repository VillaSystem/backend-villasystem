package com.example.backendvillasystem.wines.domain.model.commands;

public record DeleteWineCommand(Long wineId) {

    public DeleteWineCommand {
        if (wineId == null || wineId <= 0) {
            throw new IllegalArgumentException("Wine ID cannot be null or less than 1");
        }
    }
}
