package com.example.backendvillasystem.wines.interfaces.rest.resources;

public record WineResource(
        Long id,
        String name,
        String year,
        String type,
        String country,
        String producer
) {}
