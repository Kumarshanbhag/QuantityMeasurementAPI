package com.quantitymeasurement.exception;

import com.quantitymeasurement.enums.ExceptionType;

public class QuantityException extends RuntimeException {
    public ExceptionType type;

    public QuantityException(ExceptionType type) {
        this.type = type;
    }
}
