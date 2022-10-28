package com.lp.letspay.dto;

public class CardPaymentDTO {
    private String cardNo;
    private String holderName;
    private int cvc;
    private double amount;
    private boolean status;

    public CardPaymentDTO() {
    }

    public CardPaymentDTO(String cardNo, String holderName, int cvc, double amount) {
        this.cardNo = cardNo;
        this.holderName = holderName;
        this.cvc = cvc;
        this.amount = amount;
        this.status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public double getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }
}
