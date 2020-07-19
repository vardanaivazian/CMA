package com.sflpro.cma.repository;

import com.sflpro.cma.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
