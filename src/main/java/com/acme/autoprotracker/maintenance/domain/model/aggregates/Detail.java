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

    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenanceId;


    public Detail() {
        this.description = Strings.EMPTY;
        this.amount = 0L;
        this.maintenanceId = null;
    }


    public Detail(String description, Long amount, Maintenance maintanceId) {
        this();
        this.description = description;
        this.amount = amount;
        this.maintenanceId = maintanceId;
    }

    public Detail(CreateDetailCommand command, Maintenance maintenanceId){
this();
this.description = command.description();
this.amount = command.amount();
this.maintenanceId = maintenanceId;
    }

    public Detail updateDetails(String description, Long amount, Maintenance maintenanceId) {
        this.description = description;
        this.amount = amount;
        this.maintenanceId = maintenanceId;
        return this;
    }

}
