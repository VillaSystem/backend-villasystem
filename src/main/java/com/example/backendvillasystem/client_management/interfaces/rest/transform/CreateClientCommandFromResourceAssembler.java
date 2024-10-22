package com.example.backendvillasystem.client_management.interfaces.rest.transform;

import com.example.backendvillasystem.client_management.domain.model.commands.CreateClientCommand;
import com.example.backendvillasystem.client_management.interfaces.rest.resources.CreateClientResource;

public class CreateClientCommandFromResourceAssembler {

    public static CreateClientCommand toCommandFromResource(CreateClientResource resource) {
        return new CreateClientCommand(
                resource.firstName(),
                resource.lastName(),
                resource.phone(),
                resource.address(),
                resource.country(),
                resource.city(),
                resource.dni(),
                resource.email(),
                resource.password(),
                resource.role()
        );
    }
}