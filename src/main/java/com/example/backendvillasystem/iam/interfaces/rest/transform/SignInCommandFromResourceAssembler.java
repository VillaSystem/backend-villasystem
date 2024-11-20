package com.example.backendvillasystem.iam.interfaces.rest.transform;

import com.example.backendvillasystem.iam.domain.model.commands.SignInCommand;
import com.example.backendvillasystem.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
