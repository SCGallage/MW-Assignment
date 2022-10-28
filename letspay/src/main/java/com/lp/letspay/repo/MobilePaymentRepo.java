package com.lp.letspay.repo;

import com.lp.letspay.domain.MobilePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobilePaymentRepo extends JpaRepository<MobilePayment, Long> {
}
