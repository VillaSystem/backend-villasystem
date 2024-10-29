package com.example.backendvillasystem.batch_management.applicaction.internal;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.commands.CreateBatchCommand;
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

    @Override
    public Optional<Batch> handle(CreateBatchCommand command) {
        var batchItem = new Batch(command);
        var createdBatch = batchRepository.save(batchItem);
        return Optional.empty();
    }
}
