package com.example.backendvillasystem.batch_management.domain.services;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.commands.CreateBatchCommand;
import com.example.backendvillasystem.batch_management.domain.model.commands.DeleteBatchCommand;
import com.example.backendvillasystem.batch_management.domain.model.commands.UpdateBatchCommand;

import java.util.Optional;

public interface BatchCommandService {
    Optional<Batch> handle(CreateBatchCommand command);

    Optional<Batch> handle(UpdateBatchCommand command);

    void handle(DeleteBatchCommand command);
}

