package com.example.backendvillasystem.wines.interfaces.rest;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.DeleteWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;
import com.example.backendvillasystem.wines.domain.model.queries.GetWinesByIdQuery;
import com.example.backendvillasystem.wines.domain.model.queries.GetWinesByProducerIdQuery;
import com.example.backendvillasystem.wines.domain.services.WineCommandService;
import com.example.backendvillasystem.wines.domain.services.WineQueryService;
import com.example.backendvillasystem.wines.interfaces.rest.resources.WineResource;
import com.example.backendvillasystem.wines.interfaces.rest.resources.CreateWineResource;
import com.example.backendvillasystem.wines.interfaces.rest.resources.UpdateWineResource;
import com.example.backendvillasystem.wines.interfaces.rest.transform.WineResourceFromEntityAssembler;
import com.example.backendvillasystem.wines.interfaces.rest.transform.CreateWineCommandFromResourceAssembler;
import com.example.backendvillasystem.wines.interfaces.rest.transform.UpdateWineCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/wines", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Wines", description = "Operations related to wines")
public class WineController {

    private final WineQueryService wineQueryService;
    private final WineCommandService wineCommandService;

    public WineController(WineQueryService wineQueryService, WineCommandService wineCommandService) {
        this.wineQueryService = wineQueryService;
        this.wineCommandService = wineCommandService;
    }

    @Operation(
            summary = "Create a wine item",
            description = "Create a wine item with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wine item created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<WineResource> createWine(@RequestBody CreateWineResource resource) {
        Optional<Wines> wineItem = wineCommandService
                .handle(CreateWineCommandFromResourceAssembler.toCommandFromResource(resource));
        return wineItem.map(i -> new ResponseEntity<>(WineResourceFromEntityAssembler.toResourceFromEntity(i), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<List<WineResource>> getAllWines() {
        var wines = wineQueryService.getAllWines();
        if (wines.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var wineResources = wines.stream()
                .map(WineResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(wineResources);
    }

    @Operation(
            summary = "Get wine item by ID",
            description = "Retrieve a specific wine item by its unique ID"
    )
    @GetMapping("{id}")
    public ResponseEntity<WineResource> getWineById(@PathVariable Long id) {
        Optional<Wines> wineItem = wineQueryService.handle(new GetWinesByIdQuery(id));
        return wineItem.map(i -> ResponseEntity.ok(WineResourceFromEntityAssembler.toResourceFromEntity(i)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get wines by producer ID or all wines",
            description = "Retrieve wine items either by producer ID or return all if no filter is provided"
    )
    @GetMapping
    public ResponseEntity<?> getWinesWithParameters(@Parameter(name = "params", hidden = true)
                                                    @RequestParam Map<String, String> params) {
        return getAllWines();
    }

    @PutMapping("/{wineId}")
    @Operation(summary = "Update a wine item", description = "Update a wine item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine item updated"),
            @ApiResponse(responseCode = "404", description = "Wine item not found")
    })
    public ResponseEntity<WineResource> updateWine(@PathVariable Long wineId,
                                                   @RequestBody UpdateWineResource resource) {
        var updateWineCommand = UpdateWineCommandFromResourceAssembler.toCommandFromResource(wineId, resource);
        var updatedWine = wineCommandService.handle(updateWineCommand);
        if (updatedWine.isEmpty()) return ResponseEntity.notFound().build();
        var updatedWineEntity = updatedWine.get();
        var updatedWineResource = WineResourceFromEntityAssembler.toResourceFromEntity(updatedWineEntity);
        return ResponseEntity.ok(updatedWineResource);
    }

    @DeleteMapping("/{wineId}")
    @Operation(summary = "Delete a wine item", description = "Delete a wine item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine item deleted"),
            @ApiResponse(responseCode = "404", description = "Wine item not found")
    })
    public ResponseEntity<?> deleteWine(@PathVariable Long wineId) {
        var deleteWineCommand = new DeleteWineCommand(wineId);
        wineCommandService.handle(deleteWineCommand);
        return ResponseEntity.ok("Wine item deleted");
    }
}
