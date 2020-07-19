package com.sflpro.cma.service.waiter;

import com.sflpro.cma.exception.ProductInOrderNotFoundException;
import com.sflpro.cma.model.Order;
import com.sflpro.cma.model.OrderStatus;
import com.sflpro.cma.model.ProductInOrder;
import com.sflpro.cma.model.Table;
import com.sflpro.cma.model.User;
import com.sflpro.cma.repository.OrderRepository;
import com.sflpro.cma.repository.ProductInOrderRepository;
import com.sflpro.cma.repository.TableRepository;
import com.sflpro.cma.repository.UserRepository;
import com.sflpro.cma.service.dto.OrderDto;
import com.sflpro.cma.service.dto.ProductInOrderDto;
import com.sflpro.cma.service.dto.TableDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class WaiterServiceImpl implements WaiterService {

    private final UserRepository userRepository;

    private final TableRepository tableRepository;

    private final OrderRepository orderRepository;

    private final ProductInOrderRepository productInOrderRepository;

    @Autowired
    public WaiterServiceImpl( TableRepository tableRepository, OrderRepository orderRepository, ProductInOrderRepository productInOrderRepository, UserRepository userRepository ) {
        this.tableRepository = tableRepository;
        this.orderRepository = orderRepository;
        this.productInOrderRepository = productInOrderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<TableDto> getTablesByUsername( String username ) {
        User user = userRepository.findByEmail( username );
        Set<Table> tables = tableRepository.findByUserId( user.getId() );
        if( tables != null ) {
            log.debug( "Get tables by user id: " + user.getId() + ", tables.size: " + tables.size() );
            return to( tables );
        }
        log.debug( "Get tables by user id: " + user.getId() + ", tables.size: 0" );
        return Collections.emptySet();
    }

    @Override
    public OrderDto createOrder( OrderDto orderDto ) {
        log.debug( "Create order for table " + orderDto.getTableId() );
        Order order = to( orderDto );
        order.setCreatedDate( Instant.now() );
        return to( orderRepository.save( order ) );
    }

    @Override
    public ProductInOrderDto createProductInOrder( ProductInOrderDto productInOrderDto ) {
        log.debug( "Create ProductInOrder for order: " + productInOrderDto.getOrder() + ", product: " + productInOrderDto.getProductId() + ", amount: " + productInOrderDto.getAmount() );
        ProductInOrder productInOrder = productInOrderRepository.save( to( productInOrderDto ) );
        return to( productInOrder );
    }

    @Override
    public ProductInOrderDto updateProductInOrder( ProductInOrderDto productInOrderDto ) {
        if( productInOrderDto.getId() != null && productInOrderRepository.getOne( productInOrderDto.getId() ) != null ) {
            log.debug( "Update ProductInOrder for order: " + productInOrderDto.getOrder() + ", product: " + productInOrderDto.getProductId() + ", amount: " + productInOrderDto.getAmount() );
            ProductInOrder productInOrder = productInOrderRepository.save( to( productInOrderDto ) );
            return to( productInOrder );
        }
        throw new ProductInOrderNotFoundException( "productInOrder not found for given id: " + productInOrderDto.getId() );
    }


    private ProductInOrder to( ProductInOrderDto productInOrderDto ) {
        return ProductInOrder.builder()
                .id( productInOrderDto.getId() )
                .order( to( productInOrderDto.getOrder() ) )
                .productId( productInOrderDto.getProductId() )
                .amount( productInOrderDto.getAmount() )
                .build();
    }

    private ProductInOrderDto to( ProductInOrder productInOrder ) {
        return ProductInOrderDto.builder()
                .id( productInOrder.getId() )
                .order( to( productInOrder.getOrder() ) )
                .productId( productInOrder.getProductId() )
                .amount( productInOrder.getAmount() )
                .build();
    }

    private Order to( OrderDto orderDto ) {
        return Order.builder()
                .id( orderDto.getId() )
                .productInOrders( orderDto.getProductInOrders() )
                .status( OrderStatus.valueOf( orderDto.getStatus().toUpperCase() ) )
                .tableId( orderDto.getTableId() )
                .createdDate( orderDto.getCreatedDate() )
                .build();
    }

    private OrderDto to( Order order ) {
        return OrderDto.builder()
                .id( order.getId() )
                .productInOrders( order.getProductInOrders() )
                .status( order.getStatus().getValue() )
                .tableId( order.getTableId() )
                .createdDate( order.getCreatedDate() )
                .build();
    }

    private TableDto to( Table table ) {
        return TableDto.builder()
                .id( table.getId() )
                .userId( table.getUserId() )
                .build();
    }

    private Set<TableDto> to( Set<Table> tables ) {
        HashSet<TableDto> tableDtoSet = new HashSet<>();
        for( Table table : tables ) {
            tableDtoSet.add( to( table ) );
        }
        return tableDtoSet;
    }
}
