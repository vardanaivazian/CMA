package com.sflpro.cma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductInOrderNotFoundException extends RuntimeException {
    public ProductInOrderNotFoundException() {
        super();
    }

    public ProductInOrderNotFoundException( String message ) {
        super( message );
    }

    public ProductInOrderNotFoundException( String message, Throwable cause ) {
        super( message, cause );
    }
}
