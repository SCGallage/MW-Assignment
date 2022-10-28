package com.demo.delivery.dao;

import com.demo.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryDAO extends JpaRepository<Delivery, Long> {
    public boolean existsById(Long id);
}
