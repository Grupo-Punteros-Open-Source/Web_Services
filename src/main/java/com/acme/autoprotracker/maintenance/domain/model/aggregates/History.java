package com.acme.autoprotracker.maintenance.domain.model.aggregates;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateHistoryCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;


@Getter
@Entity
public class History {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "service_date", nullable = false)
    private String service_date;

    @Getter
    @Column(name = "description", nullable = false)
    private String description;

    @Getter
    @Column(name = "cost", nullable = false)
    private Double cost;

    @Getter
    @Column(name = "mileage", nullable = false)
    private Long mileage;

    public History() {
        this.service_date = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.cost = 0.0D;
        this.mileage = 0L;
    }

    public History(String service, String description, Double cost, Long mileage) {
        this();
        this.service_date = service;
        this.description = description;
        this.cost = cost;
        this.mileage = mileage;
    }

    public History(CreateHistoryCommand command){
        this();
        this.service_date = command.service();
        this.description = command.description();
        this.cost = command.cost();
        this.mileage = command.mileage();
    }

    public History updateHistory(String service, String description, Double cost, Long mileage) {
        this.service_date = service;
        this.description = description;
        this.cost = cost;
        this.mileage = mileage;
        return this;
    }
}
