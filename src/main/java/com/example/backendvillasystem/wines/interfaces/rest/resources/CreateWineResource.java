package com.example.backendvillasystem.wines.interfaces.rest.resources;

public record CreateWineResource(
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
        String link // Incluido link como atributo obligatorio
) {
    public CreateWineResource {
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
        if (grapes == null || grapes.isBlank()) {
            throw new IllegalArgumentException("Grapes cannot be null or empty");
        }
        if (alcohol == null || alcohol <= 0) {
            throw new IllegalArgumentException("Alcohol content must be greater than 0");
        }
        if (producerId == null || producerId.isBlank()) {
            throw new IllegalArgumentException("Producer ID cannot be null or empty");
        }
        if (batchId == null || batchId.isBlank()) {
            throw new IllegalArgumentException("Batch ID cannot be null or empty");
        }
        if (link == null || link.isBlank()) { // Validación para link
            throw new IllegalArgumentException("Link cannot be null or empty");
        }
    }
}
