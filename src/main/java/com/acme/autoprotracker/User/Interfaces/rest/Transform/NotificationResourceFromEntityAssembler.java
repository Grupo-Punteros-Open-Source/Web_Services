package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Entities.Notification;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {

    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getType(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getTimestamp(),
                entity.getIsRead()
        );
    }
}