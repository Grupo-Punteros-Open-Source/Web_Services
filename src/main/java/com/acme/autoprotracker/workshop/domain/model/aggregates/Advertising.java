package com.acme.autoprotracker.workshop.domain.model.aggregates;

import com.acme.autoprotracker.workshop.domain.model.commands.CreateAdvertisingCommand;
import com.acme.autoprotracker.workshop.domain.model.commands.CreateProductCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Getter
@Entity
public class Advertising {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter@URL
    @Column (name = "imageUrl", nullable = false)
    private String image_url;

    @Getter
    @Column(name = "slogan", nullable = false)
    private String slogan;
    @Getter
    @Column(name = "message", nullable = false)
    private String message;

    @Getter
    @Column(name = "workshop_id", nullable = false)
    private Long workshopId;

    public Advertising() {
        this.name = Strings.EMPTY;
        this.image_url = Strings.EMPTY ;
        this.slogan = Strings.EMPTY ;
        this.message = Strings.EMPTY;;
        this.workshopId = 0L;
    }

    public Advertising(String name, String image_url, String slogan, String message, Long workshopId) {
        this.name = name;
        this.image_url = image_url;
        this.slogan = slogan;
        this.message = message;
        this.workshopId = workshopId;
    }

    public Advertising(CreateAdvertisingCommand command){
        this();
        this.name = command.name();
        this.image_url = command.image_url();
        this.slogan = command.slogan();
        this.message = command.message();
        this.workshopId = command.workshopId();
    }
    public Advertising updateAdvertising(String name, String image_url, String slogan, String message, Long workshopId) {
        this.name = name;
        this.image_url = image_url;
        this.slogan = slogan;
        this.message = message;
        this.workshopId = workshopId;
        return this;
    }


}
