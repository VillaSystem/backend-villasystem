package com.example.backendvillasystem.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token,
                                        java.util.Set<com.example.backendvillasystem.iam.domain.model.entities.Role> roles) {

}
