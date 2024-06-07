package com.acme.autoprotracker.maintenance.domain.model.aggregates;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateDetailCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Detail {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column (name = "description", nullable = false)
    private String description;

    @Getter
    @Column(name = "amount")
    private Long amount;

    @Getter
    @Column(name = "maintenance_id", nullable = false)
    private Long maintanceId;


    public Detail() {
        this.description = Strings.EMPTY;
        this.amount = 0L;
        this.maintanceId = 0L;
    }


    public Detail(String description, Long amount, Long maintanceId) {
        this();
        this.description = description;
        this.amount = amount;
        this.maintanceId = maintanceId;
    }

    public Detail(CreateDetailCommand command){
this();
this.description = command.description();
this.amount = command.amount();
this.maintanceId = command.maintanceId();
    }

    public Detail updateDetails(String description, Long amount, Long maintanceId) {
        this.description = description;
        this.amount = amount;
        this.maintanceId = maintanceId;
        return this;
    }

}
