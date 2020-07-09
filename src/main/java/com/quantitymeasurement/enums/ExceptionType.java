package com.quantitymeasurement.enums;

public enum ExceptionType {
    INVALID_CONVERSION(400, "Main Unit Type Should Be Same"),
    UNIT_NOT_FOUND(400, "No Such Unit Found");

    public int status;
    public String message;

    ExceptionType(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
