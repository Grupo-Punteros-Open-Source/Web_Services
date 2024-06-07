package com.acme.autoprotracker.maintenance.domain.model.aggregates;


import com.acme.autoprotracker.maintenance.domain.model.commands.CreateInvoiceCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.CreateVehicleCommand;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceCode;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceStatus;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.Plate;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Entity
public class Vehicle {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter
    @Column(name = "plate", nullable = false)
    private Plate plate;

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
    @Column(name = "imageUrl", nullable = false)
    private String image_Url;

    @Getter
    @Column(name = "mileages", nullable = false)
    private Long mileages;

    public Vehicle() {
        this.plate = new Plate("AAA-000");
        this.make = Strings.EMPTY;
        this.model = Strings.EMPTY;
        this.year = 0L;
        this.color = Strings.EMPTY;
        this.image_Url = Strings.EMPTY;
        this.mileages = 0L;
    }

    public Vehicle(Plate plate, String make, String model, Long year, String color, String image_Url, Long mileages) {
        this();
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.image_Url = image_Url;
        this.mileages = mileages;
    }
    public Vehicle(CreateVehicleCommand command){
        this();
        this.plate = command.plate();
        this.make = command.make();
        this.model = command.model();
        this.year = command.year();
        this.color = command.color();
        this.image_Url = command.image_Url();
        this.mileages = command.mileages();
    }

    public Vehicle updateVehicle(Plate plate, String make, String model, Long year, String color, String image_Url, Long mileages) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.image_Url = image_Url;
        this.mileages = mileages;
        return this;
    }


}
