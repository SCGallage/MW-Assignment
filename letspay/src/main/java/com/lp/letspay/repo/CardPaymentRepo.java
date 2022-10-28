package com.lp.letspay.repo;

import com.lp.letspay.domain.CardPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardPaymentRepo extends JpaRepository<CardPayment, Long> {
}
