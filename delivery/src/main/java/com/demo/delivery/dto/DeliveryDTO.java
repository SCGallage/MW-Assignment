package com.demo.delivery.dto;

import java.sql.Date;

public class DeliveryDTO {
    Long id;
    String address;
    Date date;
    String receiverName;
    String state;

    public DeliveryDTO() {
    }

    public DeliveryDTO(Long id, String address, Date date, String receiverName, String state) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.receiverName = receiverName;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
