package com.acme.autoprotracker.User.Interfaces.rest.Resources;

public record NotificationResource(Long id, Long userId, String type, String title, String message, String timestamp, Boolean read) {
}