package com.sflpro.cma.service.manager;

import com.sflpro.cma.exception.PasswordMismatchException;
import com.sflpro.cma.model.Product;
import com.sflpro.cma.model.Table;
import com.sflpro.cma.service.dto.TableDto;
import com.sflpro.cma.service.dto.UserDto;
import com.sflpro.cma.repository.ProductRepository;
import com.sflpro.cma.service.dto.ProductDto;
import com.sflpro.cma.repository.TableRepository;
import com.sflpro.cma.service.dto.UserTablesDto;
import com.sflpro.cma.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ProductRepository productRepository;
    private final TableRepository tableRepository;
    private final UserService userService;

    @Autowired
    public ManagerServiceImpl( ProductRepository productRepository, TableRepository tableRepository, UserService userService ) {
        this.productRepository = productRepository;
        this.tableRepository = tableRepository;
        this.userService = userService;
    }

    @Override
    public UserDto get() {
        return userService.getAll().get( 0 );
    }

    @Override
    public UserDto createUser( UserDto userDto ) {
        log.debug( "Create User " + userDto.toString() );
        if( userDto.getPassword().equals( userDto.getPasswordConfirm() ) ) {
            return userService.create( userDto );
        }
        throw new PasswordMismatchException( "Password and PasswordConfirm isn't equal." );
    }

    @Override
    public ProductDto saveProduct( ProductDto productDto ) {
        log.debug( "Save product " + productDto.toString() );
        return to( productRepository.save( to( productDto ) ) );
    }

    @Override
    public TableDto saveTable( TableDto tableDto ) {
        log.debug( "Save table " + tableDto.toString() );
        Table table = tableRepository.save( to( tableDto ) );
        return to( table );
    }

    @Override
    public void assignTables( UserTablesDto userTablesDto ) {
        Set<Integer> tableIds = userTablesDto.getTableIds();
        Long userId = userTablesDto.getUserId();
        for( Integer tableId : tableIds ) {
            Table table = to( new TableDto( tableId, userId ) );
            tableRepository.save( table );
        }
        log.debug( "Assign tables to user: " + userTablesDto.toString() );
    }

    /*backdoor: not for live*/
    @Override
    public void truncateUsers() {
        userService.deleteAll();
    }

    private Table to( TableDto tableDto) {
        return Table.builder()
                .id( tableDto.getId() )
                .userId( tableDto.getUserId() )
                .build();
    }

    private TableDto to(Table table) {
        return TableDto.builder()
                .id( table.getId() )
                .userId( table.getUserId() )
                .build();
    }

    private Product to(ProductDto productDto) {
        return Product.builder()
                .name( productDto.getName() )
                .build();
    }

    private ProductDto to(Product product) {
        return ProductDto.builder()
                .id( product.getId() )
                .name( product.getName() )
                .build();
    }

    private List<ProductDto> to(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>( products.size() );
        for( Product product : products ) {
            productDtoList.add( to( product ) );
        }
        return productDtoList;
    }
}
