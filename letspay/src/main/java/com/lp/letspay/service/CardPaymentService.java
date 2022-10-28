package com.lp.letspay.service;

import com.lp.letspay.domain.CardPayment;
import com.lp.letspay.domain.CreditCard;
import com.lp.letspay.dto.CardPaymentDTO;
import com.lp.letspay.repo.CardPaymentRepo;
import com.lp.letspay.repo.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CardPaymentService implements CardPaymentServiceInterface {
    private final CreditCardRepo creditCardRepo;
    private final CardPaymentRepo cardPaymentRepo;

    @Autowired
    public CardPaymentService(CreditCardRepo creditCardRepo, CardPaymentRepo cardPaymentRepo) {
        this.creditCardRepo = creditCardRepo;
        this.cardPaymentRepo = cardPaymentRepo;
    }

    /*
     * Make payment.
     * @param CardPaymentDTO.
     * @return CardPaymentDTO.
     * */
    @Transactional
    public synchronized CardPaymentDTO pay(CardPaymentDTO cardPaymentDTO) {

        if (cardPaymentDTO.getCardNo() != null &&
                creditCardRepo.existsById(cardPaymentDTO.getCardNo())) { // check the existence of the card
            Optional<CreditCard> creditCardRead = creditCardRepo.findById(cardPaymentDTO.getCardNo()); // get CreditCard information from database

            if (creditCardRead.isPresent()) {
                CreditCard creditCard = creditCardRead.get(); //get CreditCard object form Optional<CreditCard> object.

                if (creditCard.isValidPaymentDetails(cardPaymentDTO)) { // validate payment information
                    creditCard.pay(cardPaymentDTO.getAmount()); //update credit limit

                    try {
                        cardPaymentRepo.save(new CardPayment(cardPaymentDTO.getAmount(), creditCard));
                        creditCardRepo.save(creditCard);
                        cardPaymentDTO.setStatus(true); // set payment state.
                        System.out.println("Payment success message sent.");
                        System.out.println("Payment success email sent.");
                    } catch (Exception e) {
                        System.out.println("Payment unsuccessful.");
                    }

                }
            }

        }
        return cardPaymentDTO;
    }
}
