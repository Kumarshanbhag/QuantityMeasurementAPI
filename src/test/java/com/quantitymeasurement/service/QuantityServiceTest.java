package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.MainUnits;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.exception.QuantityException;
import com.quantitymeasurement.model.UnitConverter;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class QuantityServiceTest {
    private IQuantityService quantityService;

    @BeforeEach
    void setUp() {
        quantityService = new QuantityService();
    }

    @Test
    public void givenQuantityServiceToGetMainUnits_WhenProper_ShouldReturnListOfMainUnits() {
        List expectedList = Arrays.asList(MainUnits.valueOf("LENGTH"));
        List allMainUnits = quantityService.getAllMainUnits();
        Assert.assertEquals(expectedList, allMainUnits);
    }

    @Test
    public void givenQuantityServiceToGetSubUnits_WhenGivenLength_ShouldReturnListOfSubUnitsOfTypeLength() {
        List expectedList = Arrays.asList(SubUnits.valueOf("FEET"), SubUnits.valueOf("INCH"));
        List allMainUnits = quantityService.getAllSubUnits("LENGTH");
        Assert.assertEquals(expectedList, allMainUnits);
    }

    @Test
    public void givenQuantityServiceToGetSubUnits_WhenNotCorrect_ShouldThrowException() {
        try {
            quantityService.getAllSubUnits("LENGT");
        } catch (QuantityException e) {
            Assert.assertEquals("No Main Type Found", e.getMessage());
        }
    }

    @Test
    public void givenQuantityService_WhenOFeetAndSecondUnitAsFeet_ShouldReturn0FEET() {
        UnitConverter unitConverter = new UnitConverter(0, SubUnits.FEET, SubUnits.FEET);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(0, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_WhenOFeetAndSecondUnitAsInch_ShouldReturn0Inch() {
        UnitConverter unitConverter = new UnitConverter(0, SubUnits.FEET, SubUnits.INCH);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(0, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When1FeetAndSecondUnitAsInch_ShouldReturn12Inch() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FEET, SubUnits.INCH);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(12, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When5FeetAndSecondUnitAsInch_ShouldReturn60Inch() {
        UnitConverter unitConverter = new UnitConverter(5, SubUnits.FEET, SubUnits.INCH);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(60, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_WhenOInchAndSecondUnitAsInch_ShouldReturn0Inch() {
        UnitConverter unitConverter = new UnitConverter(0, SubUnits.INCH, SubUnits.INCH);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(0, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_WhenOInchAndSecondUnitAsFeet_ShouldReturn0Feet() {
        UnitConverter unitConverter = new UnitConverter(0, SubUnits.INCH, SubUnits.FEET);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(0, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When12InchAndSecondUnitAsFeet_ShouldReturn1Feet() {
        UnitConverter unitConverter = new UnitConverter(12, SubUnits.INCH, SubUnits.FEET);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When60InchAndSecondUnitAsFeet_ShouldReturn5Feet() {
        UnitConverter unitConverter = new UnitConverter(60, SubUnits.INCH, SubUnits.FEET);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(5, convertedValue, 0.0);
    }
}