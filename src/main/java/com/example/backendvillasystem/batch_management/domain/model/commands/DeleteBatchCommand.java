package com.example.backendvillasystem.batch_management.domain.model.commands;

public record DeleteBatchCommand(Long batchId) {

    public DeleteBatchCommand {
        if (batchId == null || batchId <= 0) {
            throw new IllegalArgumentException("Batch id cannot be null or less than 1");
        }
    }
}
