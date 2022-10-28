package com.lp.letspay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class MobilePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Time time;
    private double amount;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bill", referencedColumnName = "phone", nullable = false)
    private MobileBill mobileBill;

    public MobilePayment() {
    }

    public MobilePayment(double amount, MobileBill mobileBill) {
        this.amount = amount;
        this.mobileBill = mobileBill;
        this.date = new Date(System.currentTimeMillis());
        this.time = new Time(this.date.getTime());
        this.mobileBill = mobileBill;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public MobileBill getMobileBill() {
        return mobileBill;
    }
}
