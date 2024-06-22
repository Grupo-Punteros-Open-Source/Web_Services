package com.acme.autoprotracker.User.Domain.Services;

import com.acme.autoprotracker.User.Domain.Model.Entity.Notification;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetNotificationByIdQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllNotificationsQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    Optional<Notification> handle (GetNotificationByIdQuery query);
    List<Notification> handle (GetAllNotificationsQuery query);
}