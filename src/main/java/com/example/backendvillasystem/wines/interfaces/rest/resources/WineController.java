package com.example.backendvillasystem.wines.interfaces.rest.resources;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.services.WineCommandService;
import com.example.backendvillasystem.wines.domain.services.WineQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/wines")
public class WineController {

    private final WineCommandService wineCommandService;
    private final WineQueryService wineQueryService;

    public WineController(WineCommandService wineCommandService, WineQueryService wineQueryService) {
        this.wineCommandService = wineCommandService;
        this.wineQueryService = wineQueryService;
    }

    @PostMapping
    public ResponseEntity<Wines> createWine(@RequestBody CreateWineCommand command) {
        Wines wine = wineCommandService.createWine(command);
        return ResponseEntity.ok(wine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wines> getWineById(@PathVariable Long id) {
        Optional<Wines> wine = wineQueryService.getWineById(id);
        return wine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}