package com.example.backendvillasystem.wines.domain.model.commands;

public record CreateWineCommand(
        String name,
        String description,
        String type,
        String region,
        String country,
        int year,
        String grapes,
        Double alcohol,
        String certification,
        Double rating,
        String state,
        String producerId,
        String batchId,
        String link
) {
    public CreateWineCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Region cannot be null or empty");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if ( year < 0) {
            throw new IllegalArgumentException("Year must be a positive integer");
        }
        if (grapes == null || grapes.isBlank()) {
            throw new IllegalArgumentException("Grapes cannot be null or empty");
        }
        if (alcohol == null || alcohol < 0) {
            throw new IllegalArgumentException("Alcohol percentage cannot be negative");
        }
        if (rating != null && (rating < 0 || rating > 100)) {
            throw new IllegalArgumentException("Rating must be between 0 and 100");
        }
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or empty");
        }
        if (producerId == null || producerId.isBlank()) {
            throw new IllegalArgumentException("Producer ID cannot be null or empty");
        }
        if (batchId == null || batchId.isBlank()) {
            throw new IllegalArgumentException("Batch ID cannot be null or empty");
        }
        if (link == null || link.isBlank()) {
            throw new IllegalArgumentException("Link must be a valid URL");
        }
    }
}
