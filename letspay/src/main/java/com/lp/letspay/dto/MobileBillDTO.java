package com.lp.letspay.dto;

import javax.persistence.Column;

public class MobileBillDTO {
    private String phone;
    private int pin;
    private double bill;
    private boolean state;

    public MobileBillDTO(String phone, int pin, double bill) {
        this.phone = phone;
        this.pin = pin;
        this.bill = bill;
        this.state = false;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public int getPin() {
        return pin;
    }

    public double getBill() {
        return bill;
    }

    public boolean isState() {
        return state;
    }
}
