package com.acme.autoprotracker.User.Application.internal.queryServices;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Notification;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetNotificationByIdQuery;
import com.acme.autoprotracker.User.Domain.Model.Queries.GetAllNotificationsQuery;
import com.acme.autoprotracker.User.Domain.Services.NotificationQueryService;
import com.acme.autoprotracker.User.Infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.notificationId());
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        return notificationRepository.findAll();
    }
}