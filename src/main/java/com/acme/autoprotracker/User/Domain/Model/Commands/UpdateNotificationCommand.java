package com.acme.autoprotracker.User.Domain.Model.Commands;

import java.time.ZonedDateTime;

public record UpdateNotificationCommand(Long id, Long userId, String type, String title, String message, String timestamp, Boolean read) {
}