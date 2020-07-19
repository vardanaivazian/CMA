package com.sflpro.cma.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PasswordMismatchException.class, UserAlreadyExistException.class})
    protected ResponseEntity<Object> handleConflict( RuntimeException ex ) {

        CmaExceptionBean cmaException = new CmaExceptionBean( CONFLICT );
        cmaException.setMessage( ex.getMessage() );
        log.info( cmaException.toString() );
        return new ResponseEntity<>( cmaException, cmaException.getStatus() );
    }

    @ExceptionHandler({ProductNotFoundException.class, ProductInOrderNotFoundException.class, UserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound( RuntimeException ex ) {

        CmaExceptionBean cmaException = new CmaExceptionBean( NOT_FOUND );
        cmaException.setMessage( ex.getMessage() );
        log.warn( cmaException.toString() );
        return new ResponseEntity<>( cmaException, cmaException.getStatus() );
    }

}
