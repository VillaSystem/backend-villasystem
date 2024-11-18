package com.example.backendvillasystem.inventory_management.interfaces.rest.transform;

import com.example.backendvillasystem.inventory_management.domain.model.commands.CreateInventoryCommand;
import com.example.backendvillasystem.inventory_management.interfaces.rest.resources.CreateInventoryResource;

public class CreateInventoryCommandFromResourceAssembler {

    public static CreateInventoryCommand toCommandFromResource(CreateInventoryResource resource) {
        return new CreateInventoryCommand(
                resource.name(),
                resource.type(),
                resource.unit(),
                resource.expirationDate(),
                resource.supplier(),
                resource.unitCost(),
                resource.quantity(),
                resource.lastUpdated(),
                resource.producerId() // New field for producer ID
        );
    }
}
