package com.example.backendvillasystem.client_management.interfaces.rest.transform;

import com.example.backendvillasystem.client_management.domain.model.commands.UpdateClientCommand;
import com.example.backendvillasystem.client_management.interfaces.rest.resources.UpdateClientResource;

public class UpdateClientCommandFromResourceAssembler {
    public static UpdateClientCommand toCommandFromResource(Long clientId, UpdateClientResource resource) {
        return new UpdateClientCommand(clientId, resource.firstName(), resource.lastName(), resource.phone(), resource.address(), resource.country(), resource.city(), resource.dni(), resource.email(), resource.role());
    }
}
