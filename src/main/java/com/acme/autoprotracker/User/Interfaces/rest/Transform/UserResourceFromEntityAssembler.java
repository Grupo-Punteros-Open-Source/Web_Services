package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UserResource;
import org.springframework.stereotype.Component;


public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getImageUrl(),
                entity.getUsername(),
                entity.getPassword()
        );
    }
}