package com.acme.autoprotracker.workshop.domain.model.aggregates;

import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
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
    @Column(name = "comName", nullable = false)
    private String comName;

    @URL @Getter
    @Column(name = "comImage", nullable = false)
    private String comImage;

    @ManyToOne
    @JoinColumn(name = "workshop_id") @Getter
    private Workshop workshopId;

    @URL @Getter
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "slogan", nullable = false) @Getter
    private String slogan;

    @Column(name = "priceMsg", nullable = false) @Getter
    private String priceMsg;

    @Column(name = "disMsg", nullable = false) @Getter
    private String disMsg;

    @Column(name = "repairMsg", nullable = false) @Getter
    private String repairMsg;

    public Advertising() {
        this.comName = Strings.EMPTY;
        this.comImage = Strings.EMPTY;
        this.workshopId = null;
        this.imageUrl = Strings.EMPTY;
        this.slogan = Strings.EMPTY;
        this.priceMsg = Strings.EMPTY;
        this.disMsg = Strings.EMPTY;
        this.repairMsg = Strings.EMPTY;
    }
    public Advertising(String comName, String comImage, Workshop workshopId, String imageUrl, String slogan, String priceMsg, String disMsg, String repairMsg) {
        this.comName = comName;
        this.comImage = comImage;
        this.workshopId = workshopId;
        this.imageUrl = imageUrl;
        this.slogan = slogan;
        this.priceMsg = priceMsg;
        this.disMsg = disMsg;
        this.repairMsg = repairMsg;
    }

    public Advertising(CreateAdvertisingCommand command, Workshop workshopId){
        this();
        this.comName = command.comName();
        this.comImage = command.comImage();
        this.workshopId = workshopId;
        this.imageUrl = command.imageUrl();
        this.slogan = command.slogan();
        this.priceMsg = command.priceMsg();
        this.disMsg = command.disMsg();
        this.repairMsg = command.repairMsg();
    }
    public Advertising updateAdvertising(String comName, String comImage, Workshop workshopId, String imageUrl, String slogan, String priceMsg, String disMsg, String repairMsg) {
        this.comName = comName;
        this.comImage = comImage;
        this.workshopId = workshopId;
        this.imageUrl = imageUrl;
        this.slogan = slogan;
        this.priceMsg = priceMsg;
        this.disMsg = disMsg;
        this.repairMsg = repairMsg;
        return this;
    }

}
