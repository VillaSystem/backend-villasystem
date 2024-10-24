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

    /**
     * Create a client
     * @param resource The details of the client to create
     * @return The created client, or a 400 response if the client could not be created
     */
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

    /**
     * Get a client by ID
     * @param id The unique identifier of the client
     * @return The client if found, or a 404 response if not found
     */
    @Operation(
            summary = "Get a client by ID",
            description = "Retrieve a specific client using their unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the client"),
            @ApiResponse(responseCode = "404", description = "Client not found for the given ID")
    })
    @GetMapping("{id}")
    public ResponseEntity<ClientResource> getClientById(@PathVariable Long id) {
        Optional<Clients> client = clientQueryService.handle(new GetClientsByIdQuery(id));
        return client.map(c -> ResponseEntity.ok(ClientResourceFromEntityAssembler.toResourceFromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get all clients
     * @param params The parameters to filter the clients by (optional)
     * @return A list of clients if found, or a 404 response if not found
     */
    @Operation(
            summary = "Get all clients ",
            description = "Retrieve all clients when no parameters are provided"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of clients"),
            @ApiResponse(responseCode = "404", description = "No clients found"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid parameters")
    })
    @GetMapping
    public ResponseEntity<?> getClientsWithParameters(@Parameter(name= "params", hidden = true)
                                                      @RequestParam Map<String, String> params) {
        if (params.isEmpty()) {
            return getAllClients();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Get clients by role
     * @param role The role to filter the clients by consumer or producer
     * @return A list of clients if found, or a 404 response if not found
     */
    @Operation(
            summary = "Get clients by role",
            description = "Retrieve a list of clients with the specified role"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of clients"),
            @ApiResponse(responseCode = "404", description = "No clients found for the given role")
    })
    @GetMapping("{role}")
    public ResponseEntity<List<ClientResource>> getClientsByRole(@PathVariable String role){
        var clients = clientQueryService.handle(new GetClientsByRoleQuery(role));
        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var clientResources = clients.stream()
                .map(ClientResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(clientResources);
    }

}