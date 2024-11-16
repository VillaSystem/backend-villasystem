package com.example.backendvillasystem.batch_management.interfaces.rest;


import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import com.example.backendvillasystem.batch_management.domain.model.commands.DeleteBatchCommand;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByIdQuery;
import com.example.backendvillasystem.batch_management.domain.model.queries.GetBatchesByProducerIdQuery;
import com.example.backendvillasystem.batch_management.domain.services.BatchCommandService;
import com.example.backendvillasystem.batch_management.domain.services.BatchQueryService;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.BatchResource;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.CreateBatchResource;
import com.example.backendvillasystem.batch_management.interfaces.rest.resources.UpdateBatchResource;
import com.example.backendvillasystem.batch_management.interfaces.rest.transform.BatchResourceFromEntityAssembler;
import com.example.backendvillasystem.batch_management.interfaces.rest.transform.CreateBatchCommandFromResourceAssembler;
import com.example.backendvillasystem.batch_management.interfaces.rest.transform.UpdateBatchCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/batches", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Batches", description = "Operations related to batches")
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

    @Operation(
            summary = "Get batch item by ID",
            description = "Retrieve a specific batch item by its unique ID"
    )

    @GetMapping("{id}")
    public ResponseEntity<BatchResource> getBatchById(@PathVariable Long id) {
        Optional<Batch> batchItem = batchQueryService.handle(new GetBatchesByIdQuery(id));
        return batchItem.map(i -> ResponseEntity.ok(BatchResourceFromEntityAssembler.toResourceFromEntity(i)))
                .orElseGet(()-> ResponseEntity.badRequest().build());

    }

    @Operation(
            summary = "Get batches by producer ID or all batches",
            description = "Retrieve batch items either bby producer ID or return all if no filter is provided"
    )
    @GetMapping
    public ResponseEntity<?> getAllBatchWithParameters(@Parameter(name= "params", hidden = true)
                                                       @RequestParam Map<String, String> params) {
        if (params.containsKey("producerId")) {
            return getBatchByProducerId(Long.parseLong(params.get("producerId")));
        }
        return getAllBatches();
    }
    public ResponseEntity<List<BatchResource>> getBatchByProducerId(@PathVariable Long producerId) {
        var batches = batchQueryService.handle(new GetBatchesByProducerIdQuery(producerId));
        if (batches.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var batchResources = batches.stream()
                .map(BatchResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(batchResources);
    }

    @PutMapping("/{batchId}")
    @Operation(summary = "Update batch item", description = "Update a specific batch item by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Batch item updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<BatchResource> updateBatch(@PathVariable Long batchId, @RequestBody UpdateBatchResource resource) {
        var updateBatchCommand = UpdateBatchCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedBatch = batchCommandService.handle(updateBatchCommand);
        if (updatedBatch.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var updatedBatchEntity = updatedBatch.get();
        var updatedBatchResource = BatchResourceFromEntityAssembler.toResourceFromEntity(updatedBatchEntity);
        return ResponseEntity.ok(updatedBatchResource);
    }

    @DeleteMapping("/{batchId}")
    @Operation(summary = "Delete batch item", description = "Delete a specific batch item by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Batch item deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<?> deleteBatch(@PathVariable Long batchId) {
        var deleteBatchCommand = new DeleteBatchCommand(batchId);
        batchCommandService.handle(deleteBatchCommand);
        return ResponseEntity.ok("Batch item deleted");
    }
}

