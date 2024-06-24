package com.acme.autoprotracker.iam.interfaces.rest.transform;

import com.acme.autoprotracker.iam.domain.model.aggregates.User;
import com.acme.autoprotracker.iam.domain.model.entities.Role;
import com.acme.autoprotracker.iam.interfaces.rest.resources.UserResource;

import java.util.stream.Collectors;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(Role::getStringName).collect(Collectors.toList());
        return new UserResource(entity.getId(), entity.getUsername(), roles);
    }
}