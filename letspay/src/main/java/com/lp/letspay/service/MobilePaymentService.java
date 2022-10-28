package com.lp.letspay.service;

import com.lp.letspay.domain.MobileBill;
import com.lp.letspay.domain.MobilePayment;
import com.lp.letspay.dto.MobilePaymentDTO;
import com.lp.letspay.repo.MobileBillRepo;
import com.lp.letspay.repo.MobilePaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MobilePaymentService implements MobilePaymentServiceInterface {

    private final MobilePaymentRepo mobilePaymentRepo;
    private final MobileBillRepo mobileBillRepo;

    @Autowired
    public MobilePaymentService(MobilePaymentRepo mobilePaymentRepo, MobileBillRepo mobileBillRepo) {
        this.mobilePaymentRepo = mobilePaymentRepo;
        this.mobileBillRepo = mobileBillRepo;
    }

    /*
     * Make payment.
     * @param MobilePaymentDTO object.
     * @return MobilePaymentDTO object.
     * */
    @Transactional
    public synchronized MobilePaymentDTO pay(MobilePaymentDTO mobilePaymentDTO) {
        if (mobilePaymentDTO.getPhoneNo() != null &&
                mobileBillRepo.existsById(mobilePaymentDTO.getPhoneNo())){ // check the existence of the mobile number in the database.
            Optional<MobileBill> mobilePaymentRead = mobileBillRepo.findById(mobilePaymentDTO.getPhoneNo());

            if (mobilePaymentRead.isPresent()){
                MobileBill mobileBill = mobilePaymentRead.get();

                if (mobileBill.isValidPaymentDetails(mobilePaymentDTO)){
                    mobileBill.addToBill(mobilePaymentDTO.getAmount()); //update bill entity

                    try {
                        mobilePaymentRepo.save(new MobilePayment(mobilePaymentDTO.getAmount(), mobileBill));
                        mobileBillRepo.save(mobileBill); //update bill
                        mobilePaymentDTO.setStatus(true); // set payment state
                        System.out.println("Payment success message sent.");
                        System.out.println("Payment success email sent.");
                    } catch (Exception e) {
                        System.out.println("Payment unsuccessful.");
                    }
                }
            }
        }
        return mobilePaymentDTO;
    }
}
