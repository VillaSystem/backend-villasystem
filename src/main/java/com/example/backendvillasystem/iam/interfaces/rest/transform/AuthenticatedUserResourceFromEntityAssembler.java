package com.example.backendvillasystem.iam.interfaces.rest.transform;

import com.example.backendvillasystem.iam.domain.model.aggregates.User;
import com.example.backendvillasystem.iam.domain.model.entities.Role;
import com.example.backendvillasystem.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token,user.getRoles());
    }
}
