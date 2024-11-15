package com.example.backendvillasystem.wines.interfaces.rest.resources;

public record UpdateWineResource(
        String name,
        String year,
        String type,
        String country,
        String producer
) {}
