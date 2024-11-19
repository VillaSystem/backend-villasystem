package com.example.backendvillasystem.batch_management.domain.model.commands;

public record UpdateBatchCommand(Long batchId, String batchNumber, String grape, String starDate, String litersQuantity, String ph, String temperature, String processStatus, String producerId) {
    public UpdateBatchCommand {
        if (batchId == null || batchId <= 0) {
            throw new IllegalArgumentException("Batch id cannot be null or less than 1");
        }
        if (batchNumber == null || batchNumber.isBlank()) {
            throw new IllegalArgumentException("Batch number cannot be blank");
        }
        if (grape == null || grape.isBlank()) {
            throw new IllegalArgumentException("Grape cannot be blank");
        }
        if (starDate == null || starDate.isBlank()) {
            throw new IllegalArgumentException("Star date cannot be blank");
        }
        if (litersQuantity == null || litersQuantity.isBlank()) {
            throw new IllegalArgumentException("Liters quantity cannot be blank");
        }
        if (ph == null || ph.isBlank()) {
            throw new IllegalArgumentException("Ph cannot be blank");
        }
        if (temperature == null || temperature.isBlank()) {
            throw new IllegalArgumentException("Temperature cannot be blank");
        }
        if (processStatus == null || processStatus.isBlank()) {
            throw new IllegalArgumentException("Process status cannot be blank");
        }
        if (producerId == null || producerId.isBlank()) {
            throw new IllegalArgumentException("Producer id cannot be blank");
        }
    }
}
