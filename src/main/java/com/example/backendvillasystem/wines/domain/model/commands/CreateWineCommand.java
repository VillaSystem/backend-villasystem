package com.example.backendvillasystem.wines.domain.model.commands;

import java.util.List;

public record CreateWineCommand(
        String name,
        String description,
        String type,
        String region,
        String country,
        Integer year,
        List<String> grapes,
        Double alcohol,
        String certification,
        Double rating,
        String state,
        String producerId,
        String batchId,
        String link
) {
    public CreateWineCommand {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (country == null || country.isBlank()) throw new IllegalArgumentException("Country cannot be null or empty");
        if (type == null || type.isBlank()) throw new IllegalArgumentException("Type cannot be null or empty");
        if (producerId == null || producerId.isBlank()) throw new IllegalArgumentException("Producer ID cannot be null or empty");
        if (batchId == null || batchId.isBlank()) throw new IllegalArgumentException("Batch ID cannot be null or empty");
    }
}
