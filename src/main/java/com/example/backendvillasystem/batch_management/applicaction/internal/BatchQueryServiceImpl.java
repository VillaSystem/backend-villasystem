package com.example.backendvillasystem.batch_management.applicaction.internal;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByIdQuery;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByProducerIdQuery;
import com.example.backendvillasystem.batch_management.domain.services.BatchQueryService;
import com.example.backendvillasystem.batch_management.infrastructure.persistence.jpa.BatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchQueryServiceImpl implements BatchQueryService {
    private final BatchRepository batchRepository;


    public BatchQueryServiceImpl(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    /**
     * Handles the GetBatchesByIdQuery query.
     *
     * @param query The get batches by id query.
     * @return The batch item if exists.
     * @throws IllegalArgumentException If id is null.
     * @see GetBatchesByIdQuery
     */


    @Override
    public Optional<Batch> handle(GetBatchesByIdQuery query) {
        return batchRepository.findById(query.id());
    }

    @Override
    public List<Batch> getAllBatches() { return batchRepository.findAll();}

    @Override
    public List<Batch> handle(GetBatchesByProducerIdQuery query){
        return batchRepository.findByProducerId(String.valueOf(query.producerId()));
    }
}


