package com.acme.autoprotracker.maintenance.domain.model.aggregates;


import com.acme.autoprotracker.User.Domain.Model.Aggregates.Customer;
import com.acme.autoprotracker.User.Domain.Model.Aggregates.Workshop;
import com.acme.autoprotracker.maintenance.domain.model.commands.CreateMaintenanceCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Maintenance {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Column(name = "lastVisitDate", nullable = false)
    private String lastVisitDate;

    @Getter
    @Column(name = "comment", nullable = false)
    private String comment;

    @Getter
    @ManyToOne
    @JoinColumn(name = "invoiceId", nullable = true)
    private Invoice invoiceId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshopId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicleId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "historyId", nullable = true)
    private History historyId;

    public Maintenance(String status, String lastVisitDate, String comment, Invoice invoiceId, Customer customerId, Workshop workshopId, Vehicle vehicleId, History historyId) {
        this();
        this.status = status;
        this.lastVisitDate = lastVisitDate;
        this.comment = comment;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.workshopId = workshopId;
        this.vehicleId = vehicleId;
        this.historyId = historyId;
    }
    public Maintenance() {
        this.status = Strings.EMPTY;
        this.lastVisitDate = Strings.EMPTY;
        this.comment = Strings.EMPTY ;
        this.invoiceId =  null;
        this.customerId =  null ;
        this.workshopId = null;
        this.vehicleId = null;
        this.historyId= null;
    }

    public Maintenance(CreateMaintenanceCommand command, Invoice invoiceId, Customer customerId, Workshop workshopId, Vehicle vehicleId, History historyId) {
        this();
        this.status = command.status();
        this.lastVisitDate = command.lastVisitDate();
        this.comment = command.comment();
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.workshopId = workshopId;
        this.vehicleId = vehicleId;
        this.historyId = historyId;
    }
    public Maintenance updateMaintenance(String status, String lastVisitDate, String comment, Invoice invoiceId, Customer customerId, Workshop workshopId, Vehicle vehicleId, History historyId) {
        this.status = status;
        this.lastVisitDate = lastVisitDate;
        this.comment = comment;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.workshopId = workshopId;
        this.vehicleId = vehicleId;
        this.historyId = historyId;
        return this;
    }
}
