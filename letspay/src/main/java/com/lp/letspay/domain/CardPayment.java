package com.lp.letspay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
@Entity
public class CardPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Time time;
    @Column(nullable = false)
    private double amount;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(referencedColumnName = "cardNo", name = "card_no", nullable = false)
    private CreditCard creditCard;

    public CardPayment() {
    }

    public CardPayment(double amount, CreditCard card) {
        this.amount = amount;
        this.date = new Date(System.currentTimeMillis());
        this.time = new Time(this.date.getTime());
        this.creditCard = card;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public CreditCard getCard() {
        return creditCard;
    }
}
