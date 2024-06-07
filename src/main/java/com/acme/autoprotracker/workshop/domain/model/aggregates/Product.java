package com.acme.autoprotracker.workshop.domain.model.aggregates;

import com.acme.autoprotracker.workshop.domain.model.commands.CreateProductCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.Date;

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
    @Column (name = "productImage", nullable = false)
    private String productImage;

    @Getter
    @Column(name = "workshop_id", nullable = false)
    private Long workshopId;


    public Product() {
        this.name = Strings.EMPTY;
        this.quantity =  0L;
        this.price = new BigDecimal("0.00") ;
        this.productImage = Strings.EMPTY;;
        this.workshopId = 0L;
    }


    public Product(String name, Long quantity, BigDecimal price, String productImage, Long workshopId) {
        this();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productImage = productImage;
        this.workshopId = workshopId;
    }

    public Product(CreateProductCommand command){
        this();
        this.name = command.name();
        this.quantity = command.quantity();
        this.price = command.price();
        this.productImage = command.productImage();
        this.workshopId = command.workshopId();
    }
    public Product updateProduct(String name, Long quantity, BigDecimal price, String productImage, Long workshopId) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productImage = productImage;
        this.workshopId = workshopId;
        return this;
    }


}
