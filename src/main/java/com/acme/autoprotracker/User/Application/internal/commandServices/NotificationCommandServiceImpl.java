package com.acme.autoprotracker.User.Application.internal.commandServices;

import com.acme.autoprotracker.User.Domain.Model.Entity.Notification;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateNotificationCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.UpdateNotificationCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteNotificationCommand;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.NotificationRepository;
import com.acme.autoprotracker.User.Domain.Services.NotificationCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;
   /* private final UserRepository userRepository;*/

    @Autowired
    public NotificationCommandServiceImpl(NotificationRepository notificationRepository/*, UserRepository userRepository*/) {
        this.notificationRepository = notificationRepository;
        /*this.userRepository = userRepository;*/
    }

    @Override
    public Long handle(CreateNotificationCommand command) {
        /*var userResult = userRepository.findById(command.userId());
        var user = userResult.get();*/
        /*1L por user*/
        Notification notification = new Notification(command, 1L);
        try {
            notificationRepository.save(notification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving notification: " + e.getMessage());
        }
        return notification.getId();
    }

    @Override
    public Optional<Notification> handle(UpdateNotificationCommand command) {
        var result = notificationRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Notification does not exist");
        var notificationToUpdate = result.get();

        /*var userResult = userRepository.findById(command.userId());
        if (userResult.isEmpty()) throw new UserNotFoundException(command.userId());
        var user = userResult.get();*/

        try {
            var updatedNotification = notificationRepository.save(notificationToUpdate.update(
                    1L,
                    command.type(),
                    command.title(),
                    command.message(),
                    command.timestamp(),
                    command.read()
            ));
            return Optional.of(updatedNotification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating notification: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteNotificationCommand command) {
        if (!notificationRepository.existsById(command.notificationId())) {
            throw new IllegalArgumentException("Notification does not exist");
        }
        try {
            notificationRepository.deleteById(command.notificationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting notification: " + e.getMessage());
        }
    }
}