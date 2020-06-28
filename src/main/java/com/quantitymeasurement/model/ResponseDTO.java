/*****************************************************************
 * @Purpose: To Store Response Body In Format(status, message, data)
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.model;

public class ResponseDTO {

    private final int status;
    private final String message;
    private final Object data;

    public ResponseDTO(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
