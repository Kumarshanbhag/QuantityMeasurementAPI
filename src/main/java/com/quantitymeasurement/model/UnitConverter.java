/*****************************************************************
 * @Purpose: To Get Data From Request Body
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.SubUnits;
import io.swagger.annotations.ApiModelProperty;

public class UnitConverter {
    @ApiModelProperty(notes = "Value For Conversion")
    public final double value;

    @ApiModelProperty(notes = "First Unit For Conversion")
    public final SubUnits firstUnitType;

    @ApiModelProperty(notes = "Second Unit For Conversion")
    public final SubUnits secondUnitType;

    public UnitConverter(double value, SubUnits firstUnitType, SubUnits secondUnitType) {
        this.value = value;
        this.firstUnitType = firstUnitType;
        this.secondUnitType = secondUnitType;
    }
}
