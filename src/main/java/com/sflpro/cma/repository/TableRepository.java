package com.sflpro.cma.repository;

import com.sflpro.cma.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TableRepository extends JpaRepository<Table, Long> {

    Set<Table> findByUserId(Long userId);

}
