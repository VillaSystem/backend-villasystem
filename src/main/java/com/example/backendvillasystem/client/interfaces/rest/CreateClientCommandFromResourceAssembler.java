package com.example.backendvillasystem.client.interfaces.rest;

import com.example.backendvillasystem.client.domain.model.commands.CreateClientCommand;

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