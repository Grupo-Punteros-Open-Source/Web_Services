package com.acme.autoprotracker.User.Domain.Model.Aggregates;

import com.acme.autoprotracker.User.Domain.Model.ValueObjects.UserAuthentication;
import com.acme.autoprotracker.User.Domain.Model.ValueObjects.UserData;
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UserData userData;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "user_username")),
            @AttributeOverride(name = "password", column = @Column(name = "user_password"))})
    private UserAuthentication userAuthentication;
    protected User() {
    }
    public User(UserData userData, UserAuthentication userAuthentication) {
        this.userData = userData;
        this.userAuthentication = userAuthentication;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }
}