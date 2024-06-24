package com.acme.autoprotracker.User.Domain.Model.Aggregates;


import com.acme.autoprotracker.User.Domain.Model.Commands.CreateCustomerCommand;
import com.acme.autoprotracker.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

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
        this.workshop = null;
        this.name = "default";
        this.address = "default";
        this.phone = "default";
        this.email = "default";
        this.imageUrl = "default";
    }

    public Customer(CreateCustomerCommand command, User userId, Workshop workshopId) {
        this();
        this.user = userId;
        this.workshop = workshopId;
        this.name = command.name();
        this.address = command.address();
        this.phone = command.phone();
        this.email = command.email();
        this.imageUrl = command.imageUrl();
    }

    public Customer update(User userId,Workshop workshopid ,String name, String address, String phone, String email, String imageUrl) {
        this.user = userId;
        this.workshop = workshopid;
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

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshopId) {
        this.workshop = workshopId;
    }

    public Workshop getWorkshopId() {
        return workshop;
    }

    public void setWorkshopId(Workshop workshop) {
        this.workshop = workshop;
    }

}