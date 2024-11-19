package com.example.backendvillasystem.wines.interfaces.rest.transform;

import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.interfaces.rest.resources.CreateWineResource;

public class CreateWineCommandFromResourceAssembler {
    public static CreateWineCommand toCommand(CreateWineResource resource) {
        return new CreateWineCommand(
                resource.name(),
                resource.year(),
                resource.type(),
                resource.country(),
                resource.producer()
        );
    }
}
