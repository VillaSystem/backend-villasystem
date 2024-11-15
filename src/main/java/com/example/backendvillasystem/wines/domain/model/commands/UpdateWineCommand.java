package com.example.backendvillasystem.wines.domain.model.commands;

public record UpdateWineCommand(
        String name,
        String year,
        String type,
        String country,
        String producer
) {}
