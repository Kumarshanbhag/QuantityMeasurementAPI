/*****************************************************************
 * @Purpose: To Get Data From Request Body
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.SubUnits;

public class UnitConverterDTO {
    public final double value;
    public final SubUnits firstUnitType;
    public final SubUnits secondUnitType;

    public UnitConverterDTO(double value, SubUnits firstUnitType, SubUnits secondUnitType) {
        this.value = value;
        this.firstUnitType = firstUnitType;
        this.secondUnitType = secondUnitType;
    }
}
