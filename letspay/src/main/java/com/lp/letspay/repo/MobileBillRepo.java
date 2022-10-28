package com.lp.letspay.repo;

import com.lp.letspay.domain.MobileBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileBillRepo extends JpaRepository<MobileBill, String> {
}
