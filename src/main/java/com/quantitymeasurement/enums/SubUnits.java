package com.quantitymeasurement.enums;

public enum SubUnits {
    FEET(MainUnits.LENGTH, 12),
    INCH(MainUnits.LENGTH, 1);

    public MainUnits unitType;
    public double conversionValue;

    SubUnits(MainUnits unitType, double conversionValue) {
        this.unitType = unitType;
        this.conversionValue = conversionValue;
    }
}
