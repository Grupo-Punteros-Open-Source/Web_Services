package com.acme.autoprotracker.maintenance.domain.model.aggregates;

import com.acme.autoprotracker.maintenance.domain.model.commands.CreateDetailCommand;
import com.acme.autoprotracker.maintenance.domain.model.commands.CreateInvoiceCommand;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceCode;
import com.acme.autoprotracker.maintenance.domain.model.valueobjects.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Entity
public class Invoice {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "invoiceCode", nullable = false)
    private InvoiceCode invoiceCode;

    @Getter
    @Column(name = "issueDate", nullable = false)
    private Date issueDate;

    @Getter
    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Getter
    @Column(name = "invoiceStatus", nullable = false)
    private InvoiceStatus status;

    @Getter
    @Column(name = "details", nullable = false)
    private String detail;
    public Invoice() {
        this.invoiceCode = new InvoiceCode("F0000000");
        this.issueDate = new Date();
        this.total = new BigDecimal("0.00") ;
        this.status = InvoiceStatus.NONE;
        this.detail = Strings.EMPTY;
    }

    public Invoice(InvoiceCode invoiceCode,Date issueDate, BigDecimal total, InvoiceStatus status, String detail) {
        this();
        this.invoiceCode = invoiceCode;
        this.issueDate = issueDate;
        this.total = total;
        this.status = status;
        this.detail = detail;
    }
    public Invoice(CreateInvoiceCommand command){
        this();
        this.invoiceCode = command.invoiceCode();
        this.issueDate = command.issueDate();
        this.total = command.total();
        this.status = command.status();
        this.detail = command.detail();
    }
    public Invoice updateInvoice(InvoiceCode invoiceCode,Date issueDate, BigDecimal total, InvoiceStatus status, String detail) {
        this.invoiceCode = invoiceCode;
        this.issueDate = issueDate;
        this.total = total;
        this.status = status;
        this.detail = detail;
        return this;
    }


}
