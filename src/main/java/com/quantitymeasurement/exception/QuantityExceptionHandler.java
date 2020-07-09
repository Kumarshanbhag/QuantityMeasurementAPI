package com.quantitymeasurement.exception;

import com.quantitymeasurement.enums.ExceptionType;
import com.quantitymeasurement.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuantityExceptionHandler {
    @ExceptionHandler (QuantityException.class)
    public ResponseEntity QuantityException(QuantityException e) {
        return new ResponseEntity(new Response(e.type.status, e.type.message, ""), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    public ResponseEntity UnitException() {
        return new ResponseEntity(new Response(ExceptionType.UNIT_NOT_FOUND.status, ExceptionType.UNIT_NOT_FOUND.message, ""), HttpStatus.BAD_REQUEST);
    }
}
