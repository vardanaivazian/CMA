package com.sflpro.cma.service.dto;

import com.sflpro.cma.model.ProductInOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long tableId;
    private String status;
    private Set<ProductInOrder> productInOrders;
    private Instant createdDate;
}
