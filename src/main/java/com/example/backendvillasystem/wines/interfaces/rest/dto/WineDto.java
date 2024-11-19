package com.example.backendvillasystem.wines.interfaces.rest.dto;

public record WineDto(
        Long id,
        String name,
        String year,
        String type,
        String country,
        String producer
) {}
