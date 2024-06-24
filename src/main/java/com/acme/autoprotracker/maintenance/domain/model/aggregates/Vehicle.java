package com.acme.autoprotracker.maintenance.domain.model.aggregates;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateVehicleCommand;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.Plate;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.URL;

@Getter
@Entity
public class Vehicle {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "plate", nullable = false)
    private String plate;

    @Getter
    @Column(name = "make", nullable = false)
    private String make;

    @Getter
    @Column(name = "model", nullable = false)
    private String model;

    @Getter
    @Column(name = "year", nullable = false)
    private Long year;

    @Getter
    @Column(name = "color", nullable = false)
    private String color;

    @Getter @URL
    @Column(name = "image_url", nullable = false)
    private String image_url;

    @Getter
    @Column(name = "mileage", nullable = false)
    private Long mileage;

    @Getter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer_id;

    public Vehicle() {
        this.plate = Strings.EMPTY;
        this.make = Strings.EMPTY;
        this.model = Strings.EMPTY;
        this.year = 0L;
        this.color = Strings.EMPTY;
        this.image_url = Strings.EMPTY;
        this.mileage = 0L;
    }

    public Vehicle(String plate, String make, String model, Long year, String color, String image_Url, Long mileages, Customer customer) {
        this();
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.image_url = image_Url;
        this.mileage = mileages;
        this.customer_id = customer;
    }

    public Vehicle(CreateVehicleCommand command, Customer customer){
        this();
        this.plate = command.plate();
        this.make = command.make();
        this.model = command.model();
        this.year = command.year();
        this.color = command.color();
        this.image_url = command.image_Url();
        this.mileage = command.mileages();
        this.customer_id = customer;
    }

    public Vehicle updateVehicle(String plate, String make, String model, Long year, String color, String image_Url, Long mileages) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.image_url = image_Url;
        this.mileage = mileages;
        return this;
    }


}
