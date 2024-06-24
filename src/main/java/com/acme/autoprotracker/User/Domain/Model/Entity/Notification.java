package com.acme.autoprotracker.User.Domain.Model.Entity;

import com.acme.autoprotracker.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateNotificationCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user_id;

    @Column(name="type")
    private String type;

    @Column(name="title")
    private String title;

    @Column(name="message")
    private String message;

    @Column(name="timestamp")
    private String timestamp;

    @Setter
    @Column(name="is_read")
    private Boolean isRead;

    public Notification() {
        this.user_id = null;
        this.type = "default";
        this.title = "default";
        this.message = "default";
        this.timestamp = "default";
        this.isRead = false;
    }

    public Notification(CreateNotificationCommand command, User userId) {
        this();
        this.user_id = userId;
        this.type = command.type();
        this.title = command.title();
        this.message = command.message();
        this.timestamp = command.timestamp();
        this.isRead = command.read();
    }

    public Notification update(User userId, String type, String title, String message, String timestamp, Boolean isRead) {
        this.user_id = userId;
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

    /*Long por User*/
    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User userId) {
        this.user_id = userId;}

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

}