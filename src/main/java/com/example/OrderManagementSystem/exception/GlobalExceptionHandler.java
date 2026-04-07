package com.example.OrderManagementSystem.exception;

import com.example.OrderManagementSystem.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<com.example.OrderManagementSystem.model.ErrorResponse> handleStockException(InsufficientStockException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );


        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
