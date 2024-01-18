package com.project.products.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                Instant.now(),
                HttpStatus.NOT_FOUND,
                ex.getDefaultErrorMessage(),
                ex.getDefaultDetailedMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(NoProductsFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoProductsFoundException(NoProductsFoundException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                Instant.now(),
                HttpStatus.NOT_FOUND,
                ex.getDefaultErrorMessage(),
                ex.getDefaultDetailedMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

}
