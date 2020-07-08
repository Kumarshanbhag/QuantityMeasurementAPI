package com.quantitymeasurement.exception;

import com.quantitymeasurement.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuantityExceptionHandler {
    @ExceptionHandler (QuantityException.class)
    public ResponseEntity QuantityException(QuantityException e) {
        return new ResponseEntity(new Response(400, e.type.message, ""), HttpStatus.BAD_REQUEST);
    }
}
