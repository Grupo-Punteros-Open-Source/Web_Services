package com.acme.autoprotracker.User.Domain.Model.Entities;

import jakarta.persistence.*;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.User;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateNotificationCommand;
import lombok.Getter;

@Getter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="type")
    private String type;

    @Column(name="title")
    private String title;

    @Column(name="message")
    private String message;

    @Column(name="timestamp")
    private String timestamp;

    @Column(name="is_read")
    private Boolean isRead;

    public Notification() {
        this.user = null;
        this.type = "default";
        this.title = "default";
        this.message = "default";
        this.timestamp = "default";
        this.isRead = false;
    }

    public Notification(CreateNotificationCommand command, User user) {
        this();
        this.user = user;
        this.type = command.type();
        this.title = command.title();
        this.message = command.message();
        this.timestamp = command.timestamp();
        this.isRead = command.read();
    }

    public Notification update(User user, String type, String title, String message, String timestamp, Boolean isRead) {
        this.user = user;
        this.type = type;
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}