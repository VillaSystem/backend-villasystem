package com.example.backendvillasystem.wines.interfaces.rest.controllers;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;
import com.example.backendvillasystem.wines.domain.services.WineCommandService;
import com.example.backendvillasystem.wines.domain.services.WineQueryService;
import com.example.backendvillasystem.wines.interfaces.rest.resources.CreateWineResource;
import com.example.backendvillasystem.wines.interfaces.rest.resources.UpdateWineResource;
import com.example.backendvillasystem.wines.interfaces.rest.resources.WineResource;
import com.example.backendvillasystem.wines.interfaces.rest.transform.CreateWineCommandFromResourceAssembler;
import com.example.backendvillasystem.wines.interfaces.rest.transform.UpdateWineCommandFromResourceAssembler;
import com.example.backendvillasystem.wines.interfaces.rest.transform.WineResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/wines")
public class WineController {

    private final WineCommandService wineCommandService;
    private final WineQueryService wineQueryService;

    public WineController(WineCommandService wineCommandService, WineQueryService wineQueryService) {
        this.wineCommandService = wineCommandService;
        this.wineQueryService = wineQueryService;
    }

    @Operation(summary = "Create a new wine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<WineResource> createWine(@RequestBody CreateWineResource resource) {
        CreateWineCommand command = CreateWineCommandFromResourceAssembler.toCommand(resource);
        Wines wine = wineCommandService.createWine(command);
        return ResponseEntity.ok(WineResourceFromEntityAssembler.toResource(wine));
    }

    @Operation(summary = "Update an existing wine by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine updated successfully"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<WineResource> updateWine(@PathVariable Long id, @RequestBody UpdateWineResource resource) {
        UpdateWineCommand command = UpdateWineCommandFromResourceAssembler.toCommand(resource);
        Wines updatedWine = wineCommandService.updateWine(id, command);
        return ResponseEntity.ok(WineResourceFromEntityAssembler.toResource(updatedWine));
    }

    @Operation(summary = "Get a wine by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine found"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WineResource> getWineById(@PathVariable Long id) {
        return wineQueryService.getWineById(id)
                .map(wine -> ResponseEntity.ok(WineResourceFromEntityAssembler.toResource(wine)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all wines")
    @ApiResponse(responseCode = "200", description = "List of wines")
    @GetMapping
    public List<WineResource> getAllWines() {
        return wineQueryService.getAllWines().stream()
                .map(WineResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Delete a wine by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Wine deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable Long id) {
        wineCommandService.deleteWine(id);
        return ResponseEntity.noContent().build();
    }
}