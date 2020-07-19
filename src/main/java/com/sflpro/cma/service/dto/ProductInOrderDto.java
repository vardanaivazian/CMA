package com.sflpro.cma.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductInOrderDto {
    private Long id;
    private OrderDto order;
    private Long productId;
    private int amount;
}
