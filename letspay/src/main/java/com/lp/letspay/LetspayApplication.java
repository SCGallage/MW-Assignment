package com.lp.letspay;

import com.lp.letspay.dto.CreditCardDTO;
import com.lp.letspay.dto.MobileBillDTO;
import com.lp.letspay.service.CardPaymentService;
import com.lp.letspay.service.CreditCardServiceInterface;
import com.lp.letspay.service.MobilBillServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetspayApplication implements CommandLineRunner {
	private final MobilBillServiceInterface mobilBillServiceInterface;
	private final CreditCardServiceInterface creditCardServiceInterface;

	@Autowired
	public LetspayApplication(MobilBillServiceInterface mobilBillServiceInterface, CreditCardServiceInterface creditCardServiceInterface) {
		this.mobilBillServiceInterface = mobilBillServiceInterface;
		this.creditCardServiceInterface = creditCardServiceInterface;
	}

	public static void main(String[] args) {
		SpringApplication.run(LetspayApplication.class, args);
	}

	//add dummy data to database.
	@Override
	public void run(String... args) throws Exception {
		addDummyData();
	}

	private void addDummyData(){
		//mobile bills
		mobilBillServiceInterface.addMobileBill(new MobileBillDTO("0721446785", 1223, 0));
		mobilBillServiceInterface.addMobileBill(new MobileBillDTO("0704446685", 1111, 0));
		mobilBillServiceInterface.addMobileBill(new MobileBillDTO("0713336785", 1220, 0));

		creditCardServiceInterface.addCreditCard(new CreditCardDTO("1234333366667777", "Sanka Gallage", 105, 15000));
		creditCardServiceInterface.addCreditCard(new CreditCardDTO("1111333366667777", "Shashika De Silva", 200, 20000));
		creditCardServiceInterface.addCreditCard(new CreditCardDTO("1010333366667777", "Nimal Siripala", 222, 10000));
	}

}
