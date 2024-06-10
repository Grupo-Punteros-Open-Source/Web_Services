package com.acme.autoprotracker.User.Interfaces.rest;

import org.springframework.http.HttpStatus;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetNotificationByIdQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllNotificationsQuery;
import com.acme.autoprotracker.User.Domain.Model.Entities.Notification;
import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteNotificationCommand;
import com.acme.autoprotracker.User.Domain.Services.NotificationCommandService;
import com.acme.autoprotracker.User.Domain.Services.NotificationQueryService;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CreateNotificationResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.UpdateNotificationResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.NotificationResource;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.CreateNotificationCommandFromResourceAssembler;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.UpdateNotificationCommandFromResourceAssembler;
import com.acme.autoprotracker.User.Interfaces.rest.Transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/notifications", produces = "application/json")
@Tag(name = "Notifications", description = "Notification Management Endpoints")
public class NotificationController {

    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource createNotificationResource) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(createNotificationResource);
        var notificationId = notificationCommandService.handle(createNotificationCommand);
        if (notificationId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) return ResponseEntity.badRequest().build();
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return new ResponseEntity<>(notificationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long notificationId) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) return ResponseEntity.badRequest().build();
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var getAllNotificationsQuery = new GetAllNotificationsQuery();
        var notifications = notificationQueryService.handle(getAllNotificationsQuery);
        var notificationResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationResources);
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity<NotificationResource> updateNotification(@PathVariable Long notificationId, @RequestBody UpdateNotificationResource updateNotificationResource) {
        var updateNotificationCommand = UpdateNotificationCommandFromResourceAssembler.toCommandFromResource(updateNotificationResource, notificationId);
        var updatedNotification = notificationCommandService.handle(updateNotificationCommand);
        if (updatedNotification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(updatedNotification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        var deleteNotificationCommand = new DeleteNotificationCommand(notificationId);
        notificationCommandService.handle(deleteNotificationCommand);
        return ResponseEntity.ok("Notification with given id successfully deleted");
    }
}