package com.acme.autoprotracker.maintenance.domain.model.aggregates;


import com.acme.autoprotracker.maintenance.domain.model.commands.CreateInvoiceCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.CreateMaintanceCommand;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceCode;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceStatus;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.Plate;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Entity
public class Maintance {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Column(name = "lastvisitDate", nullable = false)
    private Date lastvisitdate;

    @Getter
    @Column(name = "coment", nullable = false)
    private String coment;

    @Getter
    @Column(name = "invoiceId", nullable = false)
    private Long invoiceId;

    @Getter
    @Column(name = "customerId", nullable = false)
    private Long customerId;

    @Getter
    @Column(name = "workshopId", nullable = false)
    private Long workshopId;

    @Getter
    @Column(name = "vehicleId", nullable = false)
    private Long vehicleId;

    @Getter
    @Column(name = "historyId", nullable = false)
    private Long historyId;

    public Maintance(String status, Date lastvisitdate, String coment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
        this();
        this.status = status;
        this.lastvisitdate = lastvisitdate;
        this.coment = coment;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.workshopId = workshopId;
        this.vehicleId = vehicleId;
        this.historyId = historyId;
    }
    public Maintance() {
        this.status = Strings.EMPTY;
        this.lastvisitdate = new Date();
        this.coment = Strings.EMPTY ;
        this.invoiceId =  0L;
        this.customerId =  0L;
        this.workshopId = 0L;
        this.vehicleId = 0L;
        this.historyId= 0L;
    }

    public Maintance(CreateMaintanceCommand command){
        this();
        this.status = command.status();
        this.lastvisitdate = command.lastvisitdate();
        this.coment = command.coment();
        this.invoiceId = command.invoiceId();
        this.customerId = command.customerId();
        this.workshopId = command.workshopId();
        this.vehicleId = command.vehicleId();
        this.historyId = command.historyId();
    }
    public Maintance updateMaintance(String status, Date lastvisitdate, String coment, Long invoiceId, Long customerId, Long workshopId, Long vehicleId, Long historyId) {
        this.status = status;
        this.lastvisitdate = lastvisitdate;
        this.coment = coment;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.workshopId = workshopId;
        this.vehicleId = vehicleId;
        this.historyId = historyId;
        return this;
    }
}
