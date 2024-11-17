package com.example.backendvillasystem.wines.interfaces.rest.transform;

import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;
import com.example.backendvillasystem.wines.interfaces.rest.resources.UpdateWineResource;

public class UpdateWineCommandFromResourceAssembler {
    public static UpdateWineCommand toCommand(UpdateWineResource resource) {
        return new UpdateWineCommand(
                resource.name(),
                resource.description(),
                resource.type(),
                resource.region(),
                resource.country(),
                resource.year(),
                resource.grapes(),
                resource.alcohol(),
                resource.certification(),
                resource.rating(),
                resource.state(),
                resource.producerId(),
                resource.batchId(),
                resource.link()
        );
    }
}
