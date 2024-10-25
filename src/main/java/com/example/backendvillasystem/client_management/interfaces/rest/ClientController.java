package com.example.backendvillasystem.client_management.interfaces.rest;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.domain.model.queries.GetClientsByIdQuery;
import com.example.backendvillasystem.client_management.domain.model.queries.GetClientsByRoleQuery;
import com.example.backendvillasystem.client_management.domain.services.ClientCommandService;
import com.example.backendvillasystem.client_management.domain.services.ClientQueryService;
import com.example.backendvillasystem.client_management.interfaces.rest.resources.ClientResource;
import com.example.backendvillasystem.client_management.interfaces.rest.resources.CreateClientResource;
import com.example.backendvillasystem.client_management.interfaces.rest.transform.ClientResourceFromEntityAssembler;
import com.example.backendvillasystem.client_management.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



@RestController
@RequestMapping(value = "/api/v1/clients", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Clients", description = "Operations related to clients")
public class ClientController {

    private final ClientQueryService clientQueryService;
    private final ClientCommandService clientCommandService;

    /**
     * Constructor
     * @param clientQueryService ClientQueryService
     * @param clientCommandService ClientCommandService
     */
    public ClientController(ClientQueryService clientQueryService, ClientCommandService clientCommandService) {
        this.clientQueryService = clientQueryService;
        this.clientCommandService = clientCommandService;
    }

    @Operation(
            summary = "Create a client",
            description = "Create a client with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<ClientResource> createClient(@RequestBody CreateClientResource resource) {
        Optional<Clients> client = clientCommandService
                .handle(CreateClientCommandFromResourceAssembler.toCommandFromResource(resource));
        return client.map(c -> new ResponseEntity<>(ClientResourceFromEntityAssembler.toResourceFromEntity(c), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<List<ClientResource>> getAllClients() {
        var clients = clientQueryService.getAllClients();
        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var clientResources = clients.stream()
                .map(ClientResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(clientResources);
    }

    private ResponseEntity<List<ClientResource>> getClientsByRole(String role) {
        var clients = clientQueryService.handle(new GetClientsByRoleQuery(role));
        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var clientResources = clients.stream()
                .map(ClientResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(clientResources);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientResource> getClientById(@PathVariable Long id) {
        Optional<Clients> client = clientQueryService.handle(new GetClientsByIdQuery(id));
        return client.map(c -> ResponseEntity.ok(ClientResourceFromEntityAssembler.toResourceFromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getClientsWithParameters(@Parameter(name= "params", hidden = true)
                                                      @RequestParam Map<String, String> params) {
        if (params.containsKey("role")){
            return getClientsByRole(params.get("role"));
        } else {
            return getAllClients();
        }
    }

}