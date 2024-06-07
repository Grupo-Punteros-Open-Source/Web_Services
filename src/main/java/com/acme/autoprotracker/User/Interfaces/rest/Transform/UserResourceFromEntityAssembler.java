package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UserResource;
import org.springframework.stereotype.Component;

@Component
public class UserResourceFromEntityAssembler {

    public UserResource toResourceFromEntity(User user) {
        return new UserResource(
                user.getId(),
                user.getUserData().name(),
                user.getUserData().address(),
                user.getUserData().phone(),
                user.getUserData().email(),
                user.getUserData().imageUrl(),
                user.getUserAuthentication().username(),
                user.getUserAuthentication().password()
        );
    }
}