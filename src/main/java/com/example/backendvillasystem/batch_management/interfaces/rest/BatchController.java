package com.example.backendvillasystem.batch_management.interfaces.rest;


import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByIdQuery;
import com.example.backendvillasystem.batch_management.domain.services.BatchCommandService;
import com.example.backendvillasystem.batch_management.domain.services.BatchQueryService;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.BatchResource;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.CreateBatchResource;
import com.example.backendvillasystem.batch_management.interfaces.rest.transform.BatchResourceFromEntityAssembler;
import com.example.backendvillasystem.batch_management.interfaces.rest.transform.CreateBatchCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/batch", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Batch", description = "Operations related to batch")
public class BatchController {

    private final BatchQueryService batchQueryService;
    private final BatchCommandService batchCommandService;


    public BatchController(BatchQueryService batchQueryService, BatchCommandService batchCommandService) {
        this.batchQueryService = batchQueryService;
        this.batchCommandService = batchCommandService;
    }

    @Operation(
            summary = "Create an batch item",
            description = "Create an batch item with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Batch item created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<BatchResource> createBatch(@RequestBody CreateBatchResource resource) {
        Optional<Batch> batchItem = batchCommandService
                .handle(CreateBatchCommandFromResourceAssembler.toCommandFromResource(resource));
        return batchItem.map(i -> new ResponseEntity<>(BatchResourceFromEntityAssembler.toResourceFromEntity(i), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
    private ResponseEntity<List<BatchResource>> getAllBatches() {
        var batches = batchQueryService.getAllBatches();
        if (batches.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var batchResources = batches.stream()
                .map(BatchResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(batchResources);
    }
    @GetMapping("{id}")
    public ResponseEntity<BatchResource> getBatchById(@PathVariable Long id) {
        Optional<Batch> batchItem = batchQueryService.handle(new GetBatchesByIdQuery(id));
        return batchItem.map(i -> ResponseEntity.ok(BatchResourceFromEntityAssembler.toResourceFromEntity(i)))
                .orElseGet(()-> ResponseEntity.badRequest().build());

    }

    @GetMapping
    public ResponseEntity<?> getAllBatchWithParameters(@Parameter(name= "params", hidden = true)
                                                       @RequestParam Map<String, String> params) {
        if (params.isEmpty()) {
            return getAllBatches();
        }
        return ResponseEntity.badRequest().build();

    }
}
