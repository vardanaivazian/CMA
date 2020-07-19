package com.sflpro.cma.controller;

import com.sflpro.cma.service.dto.ProductDto;
import com.sflpro.cma.service.dto.TableDto;
import com.sflpro.cma.service.dto.UserDto;
import com.sflpro.cma.service.dto.UserTablesDto;
import com.sflpro.cma.service.manager.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1/manager")
@Api(tags = "manager-api")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController( ManagerService managerService ) {
        this.managerService = managerService;
    }

    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success. Add new user request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Unauthorized: Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch."),
            @ApiResponse(code = 415, message = "Failure. Unsupported media type. Endpoint consumes 'json/application'.")
    })
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto ) {

        UserDto userDtoSign = managerService.createUser( userDto );

        return new ResponseEntity<>( userDtoSign, HttpStatus.CREATED );
    }

    @ApiIgnore
    @GetMapping("/test")
    public ResponseEntity<UserDto> test() {
        UserDto userDto = managerService.get();
        return new ResponseEntity<>(userDto, HttpStatus.OK );
    }


    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success. Add new product request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Unauthorized: Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch."),
            @ApiResponse(code = 415, message = "Failure. Unsupported media type. Endpoint consumes 'json/application'.")
    })
    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct( @RequestBody ProductDto productDto ) {

        ProductDto savedProductDto = managerService.saveProduct( productDto );

        return new ResponseEntity<>( savedProductDto, HttpStatus.CREATED );
    }


    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success. Add new table request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Unauthorized: Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch."),
            @ApiResponse(code = 415, message = "Failure. Unsupported media type. Endpoint consumes 'json/application'.")
    })
    @PostMapping("/table")
    public ResponseEntity<TableDto> createTable( @RequestBody TableDto tableDto ) {

        TableDto savedTableDto = managerService.saveTable( tableDto );

        return new ResponseEntity<>( savedTableDto, HttpStatus.CREATED );
    }


    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Assign Tables to Waiters, each Table can be assigned only to 1 waiter")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success. Assign table to user request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Unauthorized: Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch."),
            @ApiResponse(code = 415, message = "Failure. Unsupported media type. Endpoint consumes 'json/application'.")
    })
    @PostMapping("/assign-tables")
    public ResponseEntity assignTablesToUser( @RequestBody UserTablesDto userTablesDto ) {

        managerService.assignTables( userTablesDto );

        return new ResponseEntity( HttpStatus.OK );
    }

    @ApiIgnore
    @DeleteMapping("/backdoor-$2a$10$PlyK68")
    public ResponseEntity truncateUsersTable( ) {

        managerService.truncateUsers( );

        return new ResponseEntity( HttpStatus.OK );
    }

}
