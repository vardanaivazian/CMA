package com.sflpro.cma.service.waiter;

import com.sflpro.cma.exception.NotFoundOpenOrderException;
import com.sflpro.cma.exception.OrderNotFoundException;
import com.sflpro.cma.exception.ProductInOrderNotFoundException;
import com.sflpro.cma.exception.TableAlreadyHaveOpenOrderException;
import com.sflpro.cma.exception.TableNotFoundException;
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
import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
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

        return saveOrder( orderDto );
    }

    @Override
    public OrderDto updateOrder( OrderDto orderDto ) {

        log.debug( "Update order for table " + orderDto.getTableId() );
        if( orderDto.getId() == null )
            throw new OrderNotFoundException( "Update order needs order id: " + orderDto.getId() );

        return saveOrder( orderDto );
    }

    @Override
    public ProductInOrderDto createProductInOrder( ProductInOrderDto productInOrderDto ) {
        if( productInOrderDto.getAmount() == 0 ) productInOrderDto.setAmount( 1 );
        log.debug( "Create ProductInOrder for orderId: " + productInOrderDto.getOrderId() + ", product: " + productInOrderDto.getProductId() + ", amount: " + productInOrderDto.getAmount() );
        Optional<Order> orderOptional = orderRepository.findById( productInOrderDto.getOrderId() );// order must be exist
        if( orderOptional.isPresent() ) {
            Order order = orderOptional.get();
            if( OrderStatus.OPEN.equals( order.getStatus() ) ) { // order must be open
                ProductInOrder productInOrder = to( productInOrderDto );
                productInOrder.setOrder( order );
                ProductInOrder productInOrderSaved = productInOrderRepository.save( productInOrder );
                return to( productInOrderSaved );
            }
            throw new NotFoundOpenOrderException( "Order: " + productInOrderDto.getOrderId() + ", is not open. Status: " + order.getStatus() );
        }
        throw new NotFoundOpenOrderException( "Order by given id: " + productInOrderDto.getOrderId() + " not found." );
    }

    @Override
    public ProductInOrderDto updateProductInOrder( ProductInOrderDto productInOrderDto ) {
        if( productInOrderDto.getId() != null && productInOrderRepository.findById( productInOrderDto.getId() ).isPresent() ) {
            log.debug( "Update ProductInOrder for orderId: " + productInOrderDto.getOrderId() + ", product: " + productInOrderDto.getProductId() + ", amount: " + productInOrderDto.getAmount() );
            ProductInOrder productInOrder = productInOrderRepository.save( to( productInOrderDto ) );
            return to( productInOrder );
        }
        throw new ProductInOrderNotFoundException( "productInOrder not found for given id: " + productInOrderDto.getId() );
    }


    private OrderDto saveOrder( OrderDto orderDto ) {

        if( !isTableExists( orderDto.getTableId() ) )
            throw new TableNotFoundException( "The table not found for given tableId: " + orderDto.getTableId() );

        if( isExistOpenOrderForLastDay( orderDto ) )
            throw new TableAlreadyHaveOpenOrderException( "The table: " + orderDto.getTableId() + " have Order which is still Open" );


        Order order = to( orderDto );
        order.setCreatedDate( Instant.now() );
        return to( orderRepository.save( order ) );
    }


    private boolean isExistOpenOrderForLastDay( OrderDto orderDto ) {
        /*take orders of the last day so that there are not too many (now - 24 hours)*/
        Instant tomorrow = Instant.now().minus( Period.ofDays( 1 ) );
        Set<Order> tableSet = orderRepository.findByTableIdAndCreatedDateGreaterThan( orderDto.getTableId(), tomorrow );

        for( Order order : tableSet ) {
            if( OrderStatus.OPEN.equals( order.getStatus() )
                    && !order.getId().equals( orderDto.getId() ) ) { // if not the same order (can be same if updated)
                return true;
            }
        }
        return false;
    }


    private boolean isTableExists( Integer id ) {
        Optional<Table> table = tableRepository.findById( id );
        return table.isPresent();
    }


    private ProductInOrder to( ProductInOrderDto productInOrderDto ) {
        return ProductInOrder.builder()
                .id( productInOrderDto.getId() )
                .order( Order.builder().id( productInOrderDto.getOrderId() ).build() )
                .productId( productInOrderDto.getProductId() )
                .amount( productInOrderDto.getAmount() )
                .build();
    }

    private ProductInOrderDto to( ProductInOrder productInOrder ) {
        return ProductInOrderDto.builder()
                .id( productInOrder.getId() )
                .orderId( productInOrder.getOrder().getId() )
                .productId( productInOrder.getProductId() )
                .amount( productInOrder.getAmount() )
                .build();
    }

    private Order to( OrderDto orderDto ) {
        return Order.builder()
                .id( orderDto.getId() )
                .status( OrderStatus.valueOf( orderDto.getStatus().toUpperCase() ) )
                .tableId( orderDto.getTableId() )
                .createdDate( orderDto.getCreatedDate() )
                .build();
    }

    private OrderDto to( Order order ) {
        return OrderDto.builder()
                .id( order.getId() )
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
