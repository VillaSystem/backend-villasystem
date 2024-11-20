package com.example.backendvillasystem.iam.interfaces.rest.transform;

import com.example.backendvillasystem.iam.domain.model.aggregates.User;
import com.example.backendvillasystem.iam.domain.model.entities.Role;
import com.example.backendvillasystem.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
