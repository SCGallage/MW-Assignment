package com.lp.letspay.repo;

import com.lp.letspay.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepo extends JpaRepository<CreditCard, String> {
}
