package com.lp.letspay.domain;

import com.lp.letspay.dto.MobilePaymentDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class MobileBill {
    @Id
    @Column(name = "phone", length = 10)
    private String phone;
    @Column(nullable = false)
    private int pin;
    @Column(nullable = false)
    private double bill;

    @OneToMany(mappedBy = "mobileBill", cascade = CascadeType.ALL)
    private List<MobilePayment> mobilePayments;

    public MobileBill() {
    }

    public MobileBill(String phoneNo, int pin) {
        this.phone = phoneNo;
        this.pin = pin;
        this.bill = 0;
    }

    /*
    * Increase the billed value when making payment.
    * @param: double value.
    * @return: boolean value.
    * */
    public boolean addToBill(double amount) {
        if (amount > 0) {
            this.bill += amount;
            return true;
        }
        return false;
    }

    /*
     * Validate request payment details.
     * @param: MobilePaymentDTO object.
     * @return: boolean value.
     * */
    public boolean isValidPaymentDetails(MobilePaymentDTO mobilePaymentDTO){
        return this.pin == mobilePaymentDTO.getPin() &&
                this.phone.equals(mobilePaymentDTO.getPhoneNo()) &&
                mobilePaymentDTO.getAmount() > 0;
    }
}
