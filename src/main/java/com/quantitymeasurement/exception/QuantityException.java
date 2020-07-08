package com.quantitymeasurement.exception;

public class QuantityException extends RuntimeException {
    public enum ExceptionType {
        INVALID_CONVERSION("Main Unit Type Should Be Same");

        public String message;

        ExceptionType(String message) {
            this.message = message;
        }
    }

    public ExceptionType type;

    public QuantityException(ExceptionType type) {
        this.type = type;
    }
}
