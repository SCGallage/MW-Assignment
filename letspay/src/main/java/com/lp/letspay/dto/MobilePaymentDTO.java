package com.lp.letspay.dto;

public class MobilePaymentDTO {
    private String phoneNo;
    private int pin;
    private double amount;
    private boolean status;

    public MobilePaymentDTO() {
    }

    public MobilePaymentDTO(String phoneNo, int pin, double amount) {
        this.phoneNo = phoneNo;
        this.pin = pin;
        this.amount = amount;
        this.status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getPin() {
        return pin;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }
}
