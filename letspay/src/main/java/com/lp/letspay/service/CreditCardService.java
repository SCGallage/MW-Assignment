package com.lp.letspay.service;

import com.lp.letspay.domain.CreditCard;
import com.lp.letspay.dto.CreditCardDTO;
import com.lp.letspay.repo.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService implements CreditCardServiceInterface{
    private final CreditCardRepo creditCardRepo;

    @Autowired
    public CreditCardService(CreditCardRepo creditCardRepo) {
        this.creditCardRepo = creditCardRepo;
    }

    @Override
    public CreditCardDTO addCreditCard(CreditCardDTO creditCardDTO) {
        try {
            creditCardRepo.save(new CreditCard(
                    creditCardDTO.getCardNo(),
                    creditCardDTO.getHolderName(),
                    creditCardDTO.getCvc(),
                    creditCardDTO.getCreditLimit()
            ));
            //validate save operation
            creditCardDTO.setState(true);

        } catch (Exception e) {
            System.out.println("Cannot Save credit card.");
        }
        return creditCardDTO;
    }
}
