package com.sflpro.cma.repository;

import com.sflpro.cma.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> findByTableIdAndCreatedDateGreaterThan( Integer tableId, Instant tomorrowDate );
}
