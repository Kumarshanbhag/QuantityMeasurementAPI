/*****************************************************************
 * @Purpose: To Get Data From Request Body
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.model;

public class UnitConverterDTO {
    public final double value;
    public final String firstUnitType;
    public final String secondUnitType;

    public UnitConverterDTO(double value, String firstUnitType, String secondUnitType) {
        this.value = value;
        this.firstUnitType = firstUnitType;
        this.secondUnitType = secondUnitType;
    }

    public double getValue() {
        return value;
    }

    public String getFirstUnitType() {
        return firstUnitType;
    }

    public String getSecondUnitType() {
        return secondUnitType;
    }
}
