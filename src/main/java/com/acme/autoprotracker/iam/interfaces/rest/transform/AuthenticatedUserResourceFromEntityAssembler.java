package com.acme.autoprotracker.iam.interfaces.rest.transform;

import com.acme.autoprotracker.iam.domain.model.aggregates.User;
import com.acme.autoprotracker.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token);
    }
}