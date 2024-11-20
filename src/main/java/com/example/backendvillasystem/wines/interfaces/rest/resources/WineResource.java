package com.example.backendvillasystem.wines.interfaces.rest.resources;

public record WineResource(
        Long id,
        String name,
        String description,
        String type,
        String region,
        String country,
        int year,
        String grapes,
        Double alcohol,
        String certification,
        Double rating,
        String state,
        String producerId,
        String batchId,
        String link
) {

}
