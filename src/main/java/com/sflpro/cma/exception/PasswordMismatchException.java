package com.sflpro.cma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super();
    }

    public PasswordMismatchException( String message ) {
        super( message );
    }

    public PasswordMismatchException( String message, Throwable cause ) {
        super( message, cause );
    }
}
