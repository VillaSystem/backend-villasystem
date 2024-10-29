package com.example.backendvillasystem.batch_management.domain.services;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByIdQuery;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByProducerIdQuery;

import java.util.List;
import java.util.Optional;

public interface BatchQueryService {
    Optional<Batch> handle(GetBatchesByIdQuery query);
    List<Batch> handle(GetBatchesByProducerIdQuery query);
    List<Batch> getAllBatches();

}
