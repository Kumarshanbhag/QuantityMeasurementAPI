package com.quantitymeasurement.enums;

public enum SubUnits {
    FEET(MainUnits.LENGTH, 12),
    INCH(MainUnits.LENGTH, 1),
    YARD(MainUnits.LENGTH, 36),
    CM(MainUnits.LENGTH, 0.4),

    LITRE(MainUnits.VOLUME, 1),
    GALLON(MainUnits.VOLUME, 3.78);

    public MainUnits unitType;
    public double conversionValue;

    SubUnits(MainUnits unitType, double conversionValue) {
        this.unitType = unitType;
        this.conversionValue = conversionValue;
    }
}
