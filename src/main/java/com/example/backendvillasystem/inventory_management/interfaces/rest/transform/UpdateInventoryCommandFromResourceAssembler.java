package com.example.backendvillasystem.inventory_management.interfaces.rest.transform;

import com.example.backendvillasystem.inventory_management.domain.model.commands.UpdateInventoryCommand;
import com.example.backendvillasystem.inventory_management.interfaces.rest.resources.UpdateInventoryResource;

public class UpdateInventoryCommandFromResourceAssembler {
    public static UpdateInventoryCommand toCommandFromResource(Long inventoryId, UpdateInventoryResource resource) {
        return new UpdateInventoryCommand(
                inventoryId,
                resource.name(),
                resource.type(),
                resource.unit(),
                resource.expirationDate(),
                resource.supplier(),
                resource.unitCost(),
                resource.quantity(),
                resource.lastUpdated(),
                resource.producerId()
        );
    }
}
