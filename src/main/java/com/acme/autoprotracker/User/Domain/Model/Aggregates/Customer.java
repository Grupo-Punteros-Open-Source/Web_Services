package com.acme.autoprotracker.User.Domain.Model.Aggregates;


import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.User.Domain.Model.Commands.CreateNotificationCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)*/
    @Column(name = "user_id")
    private Long user;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "address")
    private String address;

    @Getter
    @Setter
    @Column(name = "phone")
    private String phone;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name="image_Url")
    private String imageUrl;

    public Customer() {
        this.user = null;
        this.name = "default";
        this.address = "default";
        this.phone = "default";
        this.email = "default";
        this.imageUrl = "default";
    }
    /*Long por User*/
    public Customer(CreateCustomerCommand command, Long userId) {
        this();
        this.user = userId;
        this.name = command.name();
        this.address = command.address();
        this.phone = command.phone();
        this.email = command.email();
        this.imageUrl = command.imageUrl();
    }
    /*Long por User*/
    public Customer update(Long userId, String name, String address, String phone, String email, String imageUrl) {
        this.user = userId;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.imageUrl = imageUrl;
        return this;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*Long por User*/
    public Long getUserId() {
        return userId;
    }

    /*Long por User*/
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUser() {
        return user;
    }

    /*Long por User*/
    public void setUser(Long user) {
        this.user = user;
    }

}