package com.example.backendvillasystem.wines.domain.model.commands;

import java.util.List;

public record UpdateWineCommand(
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
