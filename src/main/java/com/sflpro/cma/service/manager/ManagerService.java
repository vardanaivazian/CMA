package com.sflpro.cma.service.manager;

import com.sflpro.cma.service.dto.ProductDto;
import com.sflpro.cma.service.dto.TableDto;
import com.sflpro.cma.service.dto.UserDto;
import com.sflpro.cma.service.dto.UserTablesDto;

public interface ManagerService {

    UserDto get();

    UserDto createUser( UserDto user );

    ProductDto saveProduct( ProductDto productDto );

    TableDto saveTable( TableDto tableDto );

    void assignTables( UserTablesDto userTablesDto );

    void truncateUsers( );

}
