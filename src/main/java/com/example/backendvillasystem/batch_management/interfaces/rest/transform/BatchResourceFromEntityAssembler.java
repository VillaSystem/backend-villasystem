package com.example.backendvillasystem.batch_management.interfaces.rest.transform;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.BatchResource;

public class BatchResourceFromEntityAssembler {
    public static BatchResource toResourceFromEntity(Batch entity){
        return new BatchResource(
                entity.getId(),
                entity.getBatchNumber(),
                entity.getGrape(),
                entity.getStarDate(),
                entity.getLitersQuantity(),
                entity.getPh(),
                entity.getTemperature(),
                entity.getProcessStatus(),
                entity.getProducerId()
        );
    }
}
