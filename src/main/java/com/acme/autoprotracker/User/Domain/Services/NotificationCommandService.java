package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Entity.Notification;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateNotificationCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateNotificationCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService {
    Long handle(CreateNotificationCommand command);
    Optional<Notification> handle(UpdateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}