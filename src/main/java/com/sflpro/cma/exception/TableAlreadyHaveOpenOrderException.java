package com.sflpro.cma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TableAlreadyHaveOpenOrderException extends RuntimeException {
    public TableAlreadyHaveOpenOrderException() {
        super();
    }

    public TableAlreadyHaveOpenOrderException( String message ) {
        super( message );
    }

    public TableAlreadyHaveOpenOrderException( String message, Throwable cause ) {
        super( message, cause );
    }
}
