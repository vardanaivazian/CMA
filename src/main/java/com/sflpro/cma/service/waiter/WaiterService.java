package com.sflpro.cma.service.waiter;

import com.sflpro.cma.service.dto.OrderDto;
import com.sflpro.cma.service.dto.ProductInOrderDto;
import com.sflpro.cma.service.dto.TableDto;

import java.util.Set;

public interface WaiterService {

    Set<TableDto> getTablesByUsername( String username );

    OrderDto createOrder( OrderDto orderDto );

    ProductInOrderDto createProductInOrder( ProductInOrderDto productInOrderDto );

    ProductInOrderDto updateProductInOrder( ProductInOrderDto productInOrderDto );

}
