package com.sflpro.cma.controller;

import com.sflpro.cma.service.dto.OrderDto;
import com.sflpro.cma.service.dto.ProductInOrderDto;
import com.sflpro.cma.service.dto.TableDto;
import com.sflpro.cma.service.waiter.WaiterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/waiter")
@Api(tags = "waiter-api")
public class WaiterController {

    private final WaiterService waiterService;

    @Autowired
    public WaiterController( WaiterService waiterService ) {
        this.waiterService = waiterService;
    }

    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Returns a list of assigned tables."),
            @ApiResponse(code = 401, message = "Failure. Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch.")
    })
    @GetMapping("/tables")
    public ResponseEntity<Set<TableDto>> getTables( Authentication authentication ) {

        User user = (User) authentication.getPrincipal();
        Set<TableDto> tables = waiterService.getTablesByUsername( user.getUsername() );

        return new ResponseEntity<>( tables, HttpStatus.CREATED );
    }


    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success. Add new order request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch.")
    })
    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder( @RequestBody OrderDto orderDto ) {

        /*order not contains ProductInOrders*/
        OrderDto order = waiterService.createOrder( orderDto );

        return new ResponseEntity<>( order, HttpStatus.CREATED );
    }

    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Update order request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch.")
    })
    @PutMapping("/order")
    public ResponseEntity<OrderDto> updateOrder( @RequestBody OrderDto orderDto ) {

        OrderDto order = waiterService.updateOrder( orderDto );

        return new ResponseEntity<>( order, HttpStatus.OK );
    }


    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success. Add new ProductInOrder request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch.")
    })
    @PostMapping("/product-in-order")
    public ResponseEntity<ProductInOrderDto> createProductInOrder( @RequestBody ProductInOrderDto productInOrderDto ) {

        ProductInOrderDto createdProductInOrderDto = waiterService.createProductInOrder( productInOrderDto );

        return new ResponseEntity<>( createdProductInOrderDto, HttpStatus.CREATED );
    }


    @ApiImplicitParam(name = "Authorization", dataType = "string", paramType = "header", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Update ProductInOrder request."),
            @ApiResponse(code = 400, message = "Failure. Indicates the request body is invalid."),
            @ApiResponse(code = 401, message = "Failure. Indicates the basic auth token is invalid."),
            @ApiResponse(code = 403, message = "Failure. Indicates the Not allowed or Role mismatch.")
    })
    @PutMapping("/product-in-order")
    public ResponseEntity<ProductInOrderDto> updateProductInOrder( @RequestBody ProductInOrderDto productInOrderDto ) {

        ProductInOrderDto createdProductInOrderDto = waiterService.updateProductInOrder( productInOrderDto );

        return new ResponseEntity<>( createdProductInOrderDto, HttpStatus.OK );
    }

}
