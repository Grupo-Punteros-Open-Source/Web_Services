package com.acme.autoprotracker.workshop.domain.model.aggregates;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
import com.acme.autoprotracker.workshop.domain.model.commands.CreateProductCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Getter
@Entity
public class Product {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Column (name = "quantity", nullable = false)
    private Long quantity;

    @Getter
    @Column (name = "price", nullable = false)
    private BigDecimal price;

    @Getter@URL
    @Column (name = "image_url", nullable = false)
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "workshop_id") @Getter
    private Workshop workshopId;


    public Product() {
        this.name = Strings.EMPTY;
        this.quantity =  0L;
        this.price = new BigDecimal("0.00") ;
        this.image_url = Strings.EMPTY;;
        this.workshopId = null;
    }


    public Product(String name, Long quantity, BigDecimal price, String productImage, Workshop workshopId) {
        this();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image_url = productImage;
        this.workshopId = workshopId;
    }

    public Product(CreateProductCommand command, Workshop workshopId) {
        this();
        this.name = command.name();
        this.quantity = command.quantity();
        this.price = command.price();
        this.image_url = command.image_url();
        this.workshopId = workshopId;
    }
    public Product updateProduct(String name, Long quantity, BigDecimal price, String productImage, Workshop workshopId) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image_url = productImage;
        this.workshopId = workshopId;
        return this;
    }


}
