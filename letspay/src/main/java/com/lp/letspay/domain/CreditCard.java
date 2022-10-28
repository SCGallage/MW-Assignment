package com.lp.letspay.domain;

import com.lp.letspay.dto.CardPaymentDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class CreditCard {
    @Id
    @Column(length = 16)
    private String cardNo;
    @Column(nullable = false)
    private String holderName;
    @Column(nullable = false)
    private int cvc;
    @Column(nullable = false)
    private double creditLimit;

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL)
    private List<CardPayment> cardPayments;

    public CreditCard() {
    }

    public CreditCard(String cardNo, String holderName, int cvc, double creditLimit) {
        this.cardNo = cardNo;
        this.holderName = holderName;
        this.cvc = cvc;
        this.creditLimit = creditLimit;
    }

    /*
     * Increase credit limit.
     * @param double value
     * @return boolean value
     * */
    public boolean deposit(double amount){
        if (amount > 0) {
            this.creditLimit += amount;
            return true;
        }

        return false;
    }

    /*
     * Pay (reduce credit limit).
     * @param double value
     * @return boolean value
     * */
    public boolean pay(double amount) {
        if (this.creditLimit >= amount){
            this.creditLimit -= amount;
            return true;
        }
        return false;
    }

    /*
    * Validate requested payment details are valid or not.
    * @param CardPaymentDTO
    * @return boolean value
    * */
    public boolean isValidPaymentDetails(CardPaymentDTO cardPaymentDTO){

        // check the eligibility of the payment
        return this.cardNo.equals(cardPaymentDTO.getCardNo()) && // check credit card number
                this.cvc == cardPaymentDTO.getCvc() && //check cvc
                this.holderName.equals(cardPaymentDTO.getHolderName()) && // check cardholder's name
                cardPaymentDTO.getAmount() > 0 && //check paying amount
                this.creditLimit >= cardPaymentDTO.getAmount();
    }

}
