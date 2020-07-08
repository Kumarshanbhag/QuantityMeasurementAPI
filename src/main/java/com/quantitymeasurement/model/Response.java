/*****************************************************************
 * @Purpose: To Store Response Body In Format(status, message, data)
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.model;

import io.swagger.annotations.ApiModelProperty;

public class Response {
    @ApiModelProperty(notes = "Status Of Opertaion")
    private final int status;

    @ApiModelProperty(notes = "Operation Message")
    private final String message;

    @ApiModelProperty(notes = "Opertaion Result")
    private final Object data;

    public Response(int status, String message, Object data) {
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
