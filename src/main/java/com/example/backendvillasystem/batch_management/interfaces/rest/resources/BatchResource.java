package com.example.backendvillasystem.batch_management.interfaces.rest.resources;

public record BatchResource(
        String batchNumber,
        String grape,
        String starDate,
        String litersQuantity,
        String ph,
        String temperature,
        String processStatus,
        String producerId

) {

}
