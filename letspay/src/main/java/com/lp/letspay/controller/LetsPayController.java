package com.lp.letspay.controller;

import com.lp.letspay.dto.CardPaymentDTO;
import com.lp.letspay.dto.MobilePaymentDTO;
import com.lp.letspay.service.CardPaymentServiceInterface;
import com.lp.letspay.service.MobilePaymentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/letspay")
public class LetsPayController {

    private final MobilePaymentServiceInterface mobilePaymentServiceInterface;
    private final CardPaymentServiceInterface cardPaymentServiceInterface;

    @Autowired
    LetsPayController(MobilePaymentServiceInterface mobilePaymentServiceInterface, CardPaymentServiceInterface cardPaymentServiceInterface){
        this.mobilePaymentServiceInterface = mobilePaymentServiceInterface;
        this.cardPaymentServiceInterface = cardPaymentServiceInterface;
    }

    @PostMapping("mobile")
    public MobilePaymentDTO payViaMobile(@RequestBody MobilePaymentDTO mobilePaymentDTO){
        return mobilePaymentServiceInterface.pay(mobilePaymentDTO);
    }

    @PostMapping("/card")
    public CardPaymentDTO payViaCreditCard(@RequestBody CardPaymentDTO cardPaymentDTO) {
        return cardPaymentServiceInterface.pay(cardPaymentDTO);
    }
}
