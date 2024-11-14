package com.example.backendvillasystem.inventory_management.interfaces.rest;

import com.example.backendvillasystem.inventory_management.domain.model.aggregates.Inventories;
import com.example.backendvillasystem.inventory_management.domain.model.commands.DeleteInventoryCommand;
import com.example.backendvillasystem.inventory_management.domain.model.commands.UpdateInventoryCommand;
import com.example.backendvillasystem.inventory_management.domain.model.queries.GetInventoriesByIdQuery;
import com.example.backendvillasystem.inventory_management.domain.model.queries.GetInventoriesByProducerIdQuery;
import com.example.backendvillasystem.inventory_management.domain.services.InventoryCommandService;
import com.example.backendvillasystem.inventory_management.domain.services.InventoryQueryService;
import com.example.backendvillasystem.inventory_management.interfaces.rest.resources.InventoryResource;
import com.example.backendvillasystem.inventory_management.interfaces.rest.resources.CreateInventoryResource;
import com.example.backendvillasystem.inventory_management.interfaces.rest.resources.UpdateInventoryResource;
import com.example.backendvillasystem.inventory_management.interfaces.rest.transform.InventoryResourceFromEntityAssembler;
import com.example.backendvillasystem.inventory_management.interfaces.rest.transform.CreateInventoryCommandFromResourceAssembler;
import com.example.backendvillasystem.inventory_management.interfaces.rest.transform.UpdateInventoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/inventories", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Inventories", description = "Operations related to inventories")
public class InventoryController {

    private final InventoryQueryService inventoryQueryService;
    private final InventoryCommandService inventoryCommandService;

    public InventoryController(InventoryQueryService inventoryQueryService, InventoryCommandService inventoryCommandService) {
        this.inventoryQueryService = inventoryQueryService;
        this.inventoryCommandService = inventoryCommandService;
    }

    @Operation(
            summary = "Create an inventory item",
            description = "Create an inventory item with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventory item created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<InventoryResource> createInventory(@RequestBody CreateInventoryResource resource) {
        Optional<Inventories> inventoryItem = inventoryCommandService
                .handle(CreateInventoryCommandFromResourceAssembler.toCommandFromResource(resource));
        return inventoryItem.map(i -> new ResponseEntity<>(InventoryResourceFromEntityAssembler.toResourceFromEntity(i), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<List<InventoryResource>> getAllInventories() {
        var inventories = inventoryQueryService.getAllInventories();
        if (inventories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var inventoryResources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(inventoryResources);
    }

    @Operation(
            summary = "Get inventory item by ID",
            description = "Retrieve a specific inventory item by its unique ID"
    )
    @GetMapping("{id}")
    public ResponseEntity<InventoryResource> getInventoryById(@PathVariable Long id) {
        Optional<Inventories> inventoryItem = inventoryQueryService.handle(new GetInventoriesByIdQuery(id));
        return inventoryItem.map(i -> ResponseEntity.ok(InventoryResourceFromEntityAssembler.toResourceFromEntity(i)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get inventories by producer ID or all inventories",
            description = "Retrieve inventory items either by producer ID or return all if no filter is provided"
    )
    @GetMapping
    public ResponseEntity<?> getInventoriesWithParameters(@Parameter(name = "params", hidden = true)
                                                          @RequestParam Map<String, String> params) {
        if (params.containsKey("producerId")) {
            return getInventoriesByProducerId(Long.parseLong(params.get("producerId")));
        }
        return getAllInventories();
    }

    public ResponseEntity<List<InventoryResource>> getInventoriesByProducerId(@PathVariable Long producerId) {
        var inventories = inventoryQueryService.handle(new GetInventoriesByProducerIdQuery(producerId));
        if (inventories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var inventoryResources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(inventoryResources);
    }

    @PutMapping("/{inventoryId}")
    @Operation(summary = "Update an inventory item", description = "Update an inventory item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory item updated"),
            @ApiResponse(responseCode = "404", description = "Inventory item not found")
    })
    public ResponseEntity<InventoryResource> updateInventory(@PathVariable Long inventoryId,
                                                             @RequestBody UpdateInventoryResource resource) {
        var updateInventoryCommand = UpdateInventoryCommandFromResourceAssembler.toCommandFromResource(inventoryId, resource);
        var updatedInventory = inventoryCommandService.handle(updateInventoryCommand);
        if (updatedInventory.isEmpty()) return ResponseEntity.notFound().build();
        var updatedInventoryEntity = updatedInventory.get();
        var updatedInventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(updatedInventoryEntity);
        return ResponseEntity.ok(updatedInventoryResource);
    }

    @DeleteMapping("/{inventoryId}")
    @Operation(summary = "Delete an inventory item", description = "Delete an inventory item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory item deleted"),
            @ApiResponse(responseCode = "404", description = "Inventory item not found")
    })
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventoryId) {
        var deleteInventoryCommand = new DeleteInventoryCommand(inventoryId);
        inventoryCommandService.handle(deleteInventoryCommand);
        return ResponseEntity.ok("Inventory item deleted");
    }
}
