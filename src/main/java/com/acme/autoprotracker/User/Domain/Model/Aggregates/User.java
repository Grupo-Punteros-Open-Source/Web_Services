package com.acme.autoprotracker.User.Domain.Model.Aggregates;

import com.acme.autoprotracker.User.Domain.Model.Commands.CreateUserCommand;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="image_Url")
    private String imageUrl;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public User() {
        this.name = "default";
        this.address = "default";
        this.phone = "default";
        this.email = "default";
        this.imageUrl = "default";
        this.username = "default";
        this.password = "default";
    }

    public User(CreateUserCommand command) {
        this();
        this.name = command.name();
        this.address = command.address();
        this.phone = command.phone();
        this.email = command.email();
        this.imageUrl = command.imageUrl();
        this.username = command.username();
        this.password = command.password();
    }

    public User update(String name,String address,String phone, String email, String imageUrl,String username, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.imageUrl = imageUrl;
        this.username = username;
        this.password = password;
        return this;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}