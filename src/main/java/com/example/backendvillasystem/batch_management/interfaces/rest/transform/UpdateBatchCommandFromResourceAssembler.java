package com.example.backendvillasystem.batch_management.interfaces.rest.transform;

import com.example.backendvillasystem.batch_management.domain.model.commands.UpdateBatchCommand;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.CreateBatchResource;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.UpdateBatchResource;

public class UpdateBatchCommandFromResourceAssembler {
    public static UpdateBatchCommand toCommandFromResource(Long batchId, UpdateBatchResource resource) {
        return new UpdateBatchCommand(
                batchId,
                resource.batchNumber(),
                resource.grape(),
                resource.starDate(),
                resource.litersQuantity(),
                resource.ph(),
                resource.temperature(),
                resource.processStatus(),
                resource.producerId()
        );
    }

}
