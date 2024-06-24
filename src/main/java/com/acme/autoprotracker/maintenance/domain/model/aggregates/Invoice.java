package com.acme.autoprotracker.maintenance.domain.model.aggregates;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateInvoiceCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Invoice {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "number", nullable = false)
    private String number;

    @Getter
    @Column(name = "issue_date", nullable = false)
    private String issue_date;

    @Getter
    @Column(name = "total", nullable = false)
    private Long total;

    @Getter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Column(name = "details", nullable = false)
    private String detail;


    public Invoice() {
        this.number ="";
        this.issue_date = "";
        this.total = 0L;
        this.status = "";
        this.detail = "";
    }

    public Invoice(String number,String issue_date, Long total, String status, String detail) {
        this();
        this.number = number;
        this.issue_date = issue_date;
        this.total = total;
        this.status = status;
        this.detail = detail;
    }
    public Invoice(CreateInvoiceCommand command){
        this();
        this.number = command.number();
        this.issue_date = command.issue_date();
        this.total = command.total();
        this.status = command.status();
        this.detail = command.detail();
    }
    public Invoice updateInvoice(String number,String issue_date, Long total, String status, String detail) {
        this.number = number;
        this.issue_date = issue_date;
        this.total = total;
        this.status = status;
        this.detail = detail;
        return this;
    }


}
