package com.example.backendvillasystem.batch_management.interfaces.rest.resources;

public record UpdateBatchResource(String batchNumber, String grape, String starDate, String litersQuantity, String ph, String temperature, String processStatus, String producerId) {
    public UpdateBatchResource {
        if (batchNumber == null || batchNumber.isBlank()) {
            throw new IllegalArgumentException("Batch number cannot be null or empty");
        }
        if (grape == null || grape.isBlank()) {
            throw new IllegalArgumentException("Grape cannot be null or empty");
        }
        if (starDate == null || starDate.isBlank()) {
            throw new IllegalArgumentException("Start date cannot be null or empty");
        }
        if (litersQuantity == null || litersQuantity.isBlank()) {
            throw new IllegalArgumentException("Liters quantity cannot be null or empty");
        }
        if (ph == null || ph.isBlank()) {
            throw new IllegalArgumentException("PH cannot be null or empty");
        }
        if (temperature == null || temperature.isBlank()) {
            throw new IllegalArgumentException("Temperature cannot be null or empty");
        }
        if (processStatus == null || processStatus.isBlank()) {
            throw new IllegalArgumentException("Process status cannot be null or empty");
        }
        if (producerId == null || producerId.isBlank()) {
            throw new IllegalArgumentException("Producer ID cannot be null or empty");
        }
    }
}
