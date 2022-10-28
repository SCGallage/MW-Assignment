package com.lp.letspay.dto;

public class CreditCardDTO {

    private String cardNo;
    private String holderName;
    private int cvc;
    private double creditLimit;
    private boolean state;

    public CreditCardDTO(String cardNo, String holderName, int cvc, double creditLimit) {
        this.cardNo = cardNo;
        this.holderName = holderName;
        this.cvc = cvc;
        this.creditLimit = creditLimit;
        this.state = false;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public int getCvc() {
        return cvc;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public boolean isState() {
        return state;
    }
}
