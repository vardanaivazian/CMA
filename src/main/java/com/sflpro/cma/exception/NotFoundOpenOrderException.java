package com.sflpro.cma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundOpenOrderException extends RuntimeException {
    public NotFoundOpenOrderException() {
        super();
    }

    public NotFoundOpenOrderException( String message ) {
        super( message );
    }

    public NotFoundOpenOrderException( String message, Throwable cause ) {
        super( message, cause );
    }
}
