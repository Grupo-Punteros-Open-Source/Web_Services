package com.acme.autoprotracker.iam.interfaces.rest.transform;

import com.acme.autoprotracker.iam.domain.model.entities.Role;
import com.acme.autoprotracker.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());
    }
}