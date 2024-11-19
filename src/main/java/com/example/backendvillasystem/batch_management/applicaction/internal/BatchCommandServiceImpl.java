package com.example.backendvillasystem.batch_management.applicaction.internal;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.commands.CreateBatchCommand;
import com.example.backendvillasystem.batch_management.domain.model.commands.DeleteBatchCommand;
import com.example.backendvillasystem.batch_management.domain.model.commands.UpdateBatchCommand;
import com.example.backendvillasystem.batch_management.domain.services.BatchCommandService;
import com.example.backendvillasystem.batch_management.infrastructure.persistence.jpa.BatchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BatchCommandServiceImpl implements BatchCommandService {
    private final BatchRepository batchRepository;
    public BatchCommandServiceImpl(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    /**
     * Handle the create batch command
     *
     * @param command The create batch command
     * @return The created batch
     * @throws IllegalArgumentException If the batch already exists
     * @see CreateBatchCommand
     */

    @Override
    public Optional<Batch> handle(CreateBatchCommand command) {
        var batchItem = new Batch(command);
        var createdBatchItem = batchRepository.save(batchItem);
        return Optional.of(createdBatchItem);
    }

    @Override
    public Optional<Batch> handle(UpdateBatchCommand command){
        var result = batchRepository.findById(command.batchId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Batch item with id %s not found".formatted(command.batchId()));
        }

        var batchToUpdate = result.get();
        try {
            var updatedBatch = batchRepository.save(batchToUpdate.updateBatch(
                    command.batchNumber(), command.grape(), command.starDate(), command.litersQuantity(), command.ph(), command.temperature(), command.processStatus(), command.producerId()));
            return Optional.of(updatedBatch);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating batch item: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteBatchCommand command) {
        if (!batchRepository.existsById(command.batchId())) {
            throw new IllegalArgumentException("Batch item with id %s not found".formatted(command.batchId()));
        }
        try {
            batchRepository.deleteById(command.batchId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting batch item: %s".formatted(e.getMessage()));
        }
    }


}

