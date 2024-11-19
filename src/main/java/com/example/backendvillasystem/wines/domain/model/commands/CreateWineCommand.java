package com.example.backendvillasystem.wines.domain.model.commands;

public record CreateWineCommand(
        String name,
        String year,
        String type,
        String country,
        String producer
) {
    public CreateWineCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Wine name cannot be null or empty");
        }
        if (year == null || year.isBlank()) {
            throw new IllegalArgumentException("Year cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (producer == null || producer.isBlank()) {
            throw new IllegalArgumentException("Producer cannot be null or empty");
        }
    }
}
