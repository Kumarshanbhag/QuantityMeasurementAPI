package com.quantitymeasurement.enums;

public enum SubUnits {
    FEET(MainUnits.LENGTH, 12),
    INCH(MainUnits.LENGTH, 1),
    YARD(MainUnits.LENGTH, 36),
    CM(MainUnits.LENGTH, 0.4),

    LITRE(MainUnits.VOLUME, 1),
    GALLON(MainUnits.VOLUME, 3.78),
    ML(MainUnits.VOLUME, 0.001),

    KG(MainUnits.WEIGHT, 1),
    GRAMS(MainUnits.WEIGHT, 0.001),
    TONNE(MainUnits.WEIGHT, 1000),

    CELSIUS(MainUnits.TEMPERATURE, 1.8),
    FAHRENHEIT(MainUnits.TEMPERATURE, 0.5555555555555556);

    public MainUnits unitType;
    public double conversionValue;

    SubUnits(MainUnits unitType, double conversionValue) {
        this.unitType = unitType;
        this.conversionValue = conversionValue;
    }
}
