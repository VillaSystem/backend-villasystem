package com.example.backendvillasystem.client.interfaces.rest;

import com.example.backendvillasystem.client.applicaction.internal.ClientCommandServiceImpl;
import com.example.backendvillasystem.client.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client.infrastructure.persistence.jpa.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/clients", produces = APPLICATION_JSON_VALUE)
public class ClientController {

    private final ClientCommandServiceImpl clientCommandService;
    private final ClientRepository clientRepository;

    public ClientController(ClientCommandServiceImpl clientCommandService, ClientRepository clientRepository) {
        this.clientCommandService = clientCommandService;
        this.clientRepository = clientRepository;
    }

    @PostMapping
    public ResponseEntity<ClientResource> createClient(@RequestBody CreateClientResource resource) {
        Optional<Clients> clients = clientCommandService.handle(
                CreateClientCommandFromResourceAssembler.toCommandFromResource(resource));

        return clients.map(source -> new ResponseEntity<>(ClientResourceFromEntityAssembler.
                        toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResource> getClientById(@PathVariable Long id) {
        Optional<Clients> client = clientRepository.findById(id);

        return client.map(c -> ResponseEntity.ok(ClientResourceFromEntityAssembler.toResourceFromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}