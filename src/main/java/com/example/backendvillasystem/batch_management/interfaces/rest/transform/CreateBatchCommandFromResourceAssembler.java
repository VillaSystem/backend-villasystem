package com.example.backendvillasystem.batch_management.interfaces.rest.transform;

import com.example.backendvillasystem.batch_management.domain.model.commands.CreateBatchCommand;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.CreateBatchResource;

public class CreateBatchCommandFromResourceAssembler {

    public static CreateBatchCommand toCommandFromResource(CreateBatchResource resource) {
        return new CreateBatchCommand(
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
