package com.example.backendvillasystem.wines.domain.model.commands;

public record UpdateWineCommand(
        Long wineId,
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
    public UpdateWineCommand {
        if (wineId == null || wineId <= 0) {
            throw new IllegalArgumentException("Wine ID cannot be null or less than 1");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or blank");
        }
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Region cannot be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }
        if (grapes == null || grapes.isBlank()) {
            throw new IllegalArgumentException("Grapes cannot be null or blank");
        }
        if (alcohol == null || alcohol < 0) {
            throw new IllegalArgumentException("Alcohol percentage cannot be null or negative");
        }
        if (rating != null && (rating < 0 || rating > 100)) {
            throw new IllegalArgumentException("Rating must be between 0 and 100");
        }
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or blank");
        }
        if (producerId == null || producerId.isBlank()) {
            throw new IllegalArgumentException("Producer ID cannot be null or blank");
        }
        if (batchId == null || batchId.isBlank()) {
            throw new IllegalArgumentException("Batch ID cannot be null or blank");
        }
        if (link != null && !link.startsWith("http")) {
            throw new IllegalArgumentException("Link must be a valid URL");
        }
    }
}
