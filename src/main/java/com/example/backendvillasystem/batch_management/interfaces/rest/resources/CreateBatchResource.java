package com.example.backendvillasystem.batch_management.interfaces.rest.resources;

public record CreateBatchResource(
        String batchNumber,
        String grape,
        String starDate,
        String litersQuantity,
        String ph,
        String temperature,
        String processStatus,
        String producerId
) {
    public CreateBatchResource {
        if (batchNumber == null || batchNumber.isBlank()) {
            throw new IllegalArgumentException("batchNumber cannot be null or empty");
        }
        if (grape == null || grape.isBlank()) {
            throw new IllegalArgumentException("grape cannot be null or empty");
        }
        if (starDate == null || starDate.isBlank()) {
            throw new IllegalArgumentException("starDate cannot be null or empty");
        }
        if (litersQuantity == null || litersQuantity.isBlank()) {
            throw new IllegalArgumentException("litersQuantity cannot be null or empty");
        }
        if (ph == null || ph.isBlank()) {
            throw new IllegalArgumentException("ph cannot be null or empty");
        }
        if (temperature == null || temperature.isBlank()) {
            throw new IllegalArgumentException("temperature cannot be null or empty");
        }
        if (processStatus == null || processStatus.isBlank()) {
            throw new IllegalArgumentException("processStatus cannot be null or empty");
        }
        if (producerId == null || producerId.isBlank()) {
            throw new IllegalArgumentException("producerId cannot be null or empty");
        }
    }
}
