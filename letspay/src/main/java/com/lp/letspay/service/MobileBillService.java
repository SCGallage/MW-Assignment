package com.lp.letspay.service;

import com.lp.letspay.domain.MobileBill;
import com.lp.letspay.dto.MobileBillDTO;
import com.lp.letspay.repo.MobileBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileBillService implements MobilBillServiceInterface{
    private final MobileBillRepo mobileBillRepo;

    @Autowired
    public MobileBillService(MobileBillRepo mobileBillRepo) {
        this.mobileBillRepo = mobileBillRepo;
    }

    @Override
    public MobileBillDTO addMobileBill(MobileBillDTO mobileBillDTO) {
        try {
            mobileBillRepo.save(new MobileBill(
                    mobileBillDTO.getPhone(),
                    mobileBillDTO.getPin()
            ));

            //save operation validation
            mobileBillDTO.setState(true);
        } catch (Exception e){
            System.out.println("Cannot save Mobile Bill.");
        }
        return mobileBillDTO;
    }
}
