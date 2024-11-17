package com.example.backendvillasystem.wines.interfaces.rest.resources;

import java.util.List;

public record WineResource(
        Long id,
        String name,
        String description,
        String type,
        String region,
        String country,
        Integer year,
        List<String> grapes,
        Double alcohol,
        String certification,
        Double rating,
        String state,
        String producerId,
        String batchId,
        String link
) {}
