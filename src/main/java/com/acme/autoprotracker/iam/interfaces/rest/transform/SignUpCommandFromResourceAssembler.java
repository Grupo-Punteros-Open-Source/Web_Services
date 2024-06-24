package com.acme.autoprotracker.iam.interfaces.rest.transform;

import com.acme.autoprotracker.iam.domain.model.commands.SignUpCommand;
import com.acme.autoprotracker.iam.domain.model.entities.Role;
import com.acme.autoprotracker.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null
                ? resource.roles().stream().map(Role::toRoleFromName).collect(Collectors.toList())
                : new ArrayList<Role>();
        return new SignUpCommand(resource.username(), resource.password(), roles);
    }
}