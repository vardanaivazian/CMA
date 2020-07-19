package com.sflpro.cma.repository;

import com.sflpro.cma.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
