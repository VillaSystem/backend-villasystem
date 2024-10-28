package com.example.backendvillasystem.batch_management.domain.services;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BatchQueryService {
    Optional<Batch> handle(GetBatchesByIdQuery query);

    List<com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch> getAllBatches();

}
