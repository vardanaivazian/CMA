package com.sflpro.cma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException( String message ) {
        super( message );
    }

    public OrderNotFoundException( String message, Throwable cause ) {
        super( message, cause );
    }
}
