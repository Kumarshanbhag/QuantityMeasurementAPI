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

    //Test For Getting All Mainunits
    @Test
    public void givenQuantityServiceToGetMainUnits_WhenProper_ShouldReturnListOfMainUnits() {
        List expectedList = Arrays.asList(MainUnits.valueOf("LENGTH"), MainUnits.valueOf("VOLUME"),
                MainUnits.valueOf("WEIGHT"), MainUnits.valueOf("TEMPERATURE"));
        List allMainUnits = quantityService.getAllMainUnits();
        Assert.assertEquals(expectedList, allMainUnits);
    }

    //Test for Getting All SubUnits Based On MainUnitType
    @Test
    public void givenQuantityServiceToGetSubUnits_WhenGivenLength_ShouldReturnListOfSubUnitsOfTypeLength() {
        List expectedList = Arrays.asList(SubUnits.valueOf("FEET"), SubUnits.valueOf("INCH"),
                SubUnits.valueOf("YARD"), SubUnits.valueOf("CM"));
        List allMainUnits = quantityService.getAllSubUnits(MainUnits.valueOf("LENGTH"));
        Assert.assertEquals(expectedList, allMainUnits);
    }

    @Test
    public void givenQuantityServiceToGetSubUnits_WhenGivenVolume_ShouldReturnListOfSubUnitsOfTypeVolume() {
        List expectedList = Arrays.asList(SubUnits.valueOf("LITRE"), SubUnits.valueOf("GALLON"),
                SubUnits.valueOf("ML"));
        List allMainUnits = quantityService.getAllSubUnits(MainUnits.valueOf("VOLUME"));
        Assert.assertEquals(expectedList, allMainUnits);
    }

    @Test
    public void givenQuantityServiceToGetSubUnits_WhenGivenWeight_ShouldReturnListOfSubUnitsOfTypeWeight() {
        List expectedList = Arrays.asList(SubUnits.valueOf("KG"), SubUnits.valueOf("GRAMS"),
                SubUnits.valueOf("TONNE"));
        List allMainUnits = quantityService.getAllSubUnits(MainUnits.valueOf("WEIGHT"));
        Assert.assertEquals(expectedList, allMainUnits);
    }

    @Test
    public void givenQuantityServiceToGetSubUnits_WhenGivenTemperature_ShouldReturnListOfSubUnitsOfTypeTemperature() {
        List expectedList = Arrays.asList(SubUnits.valueOf("CELSIUS"), SubUnits.valueOf("FAHRENHEIT"));
        List allMainUnits = quantityService.getAllSubUnits(MainUnits.valueOf("TEMPERATURE"));
        Assert.assertEquals(expectedList, allMainUnits);
    }

    //Test For Conversion Of Feet To Inch and vice Versa
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

    //Test For Conversion Of Yard and To Other Length Type And vice Versa
    @Test
    public void givenQuantityService_When3FeetAndSecondUnitAsYard_ShouldReturn1Yard() {
        UnitConverter unitConverter = new UnitConverter(3, SubUnits.FEET, SubUnits.YARD);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When1YardAndSecondUnitAsFeet_ShouldReturn3Feet() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.YARD, SubUnits.FEET);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(3, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When36InchAndSecondUnitAsYard_ShouldReturn1Yard() {
        UnitConverter unitConverter = new UnitConverter(36, SubUnits.INCH, SubUnits.YARD);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When1YardAndSecondUnitAsInch_ShouldReturn35Inch() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.YARD, SubUnits.INCH);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(36, convertedValue, 0.0);
    }

    //Test For Conversion Of Centimeter and To Other Length Type And vice Versa
    @Test
    public void givenQuantityService_When1FeetAndSecondUnitAsCentimeter_ShouldReturn30Centimeter() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FEET, SubUnits.CM);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(30, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When30CentimeterAndSecondUnitAsFeet_ShouldReturn1Feet() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.YARD, SubUnits.FEET);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(3, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When2InchAndSecondUnitAsCentimeter_ShouldReturn5Centimeter() {
        UnitConverter unitConverter = new UnitConverter(2, SubUnits.INCH, SubUnits.CM);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(5, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When5CentimeterAndSecondUnitAsInch_ShouldReturn2Inch() {
        UnitConverter unitConverter = new UnitConverter(5, SubUnits.CM, SubUnits.INCH);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(2, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When1YardAndSecondUnitAsCentimeter_ShouldReturn90Centimeter() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.YARD, SubUnits.CM);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(90, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When90CentimeterAndSecondUnitAsInch_ShouldReturn1Yard() {
        UnitConverter unitConverter = new UnitConverter(90, SubUnits.CM, SubUnits.YARD);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1, convertedValue, 0.0);
    }

    //Test For Different Main Unit Convert Values
    @Test
    public void givenQuantityService_When1FeetAndSecondUnitAsLiter_ShouldReturnException() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FEET, SubUnits.LITRE);
        try{
            quantityService.getConvertedValue(unitConverter);
        } catch (QuantityException e){
            Assert.assertEquals("Main Unit Type Should Be Same",e.getMessage());
        }
    }

    @Test
    public void givenQuantityService_When1FeetAndSecondUnitAsKG_ShouldReturnException() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FEET, SubUnits.KG);
        try{
            quantityService.getConvertedValue(unitConverter);
        } catch (QuantityException e){
            Assert.assertEquals("Main Unit Type Should Be Same",e.getMessage());
        }
    }

    @Test
    public void givenQuantityService_When1FeetAndSecondUnitAsCelsius_ShouldReturnException() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FEET, SubUnits.CELSIUS);
        try{
            quantityService.getConvertedValue(unitConverter);
        } catch (QuantityException e){
            Assert.assertEquals("Main Unit Type Should Be Same",e.getMessage());
        }
    }

    @Test
    public void givenQuantityService_When1LitreAndSecondUnitAsKG_ShouldReturnException() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.LITRE, SubUnits.KG);
        try{
            quantityService.getConvertedValue(unitConverter);
        } catch (QuantityException e){
            Assert.assertEquals("Main Unit Type Should Be Same",e.getMessage());
        }
    }

    @Test
    public void givenQuantityService_When1LitreAndSecondUnitAsCelsius_ShouldReturnException() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.LITRE, SubUnits.CELSIUS);
        try{
            quantityService.getConvertedValue(unitConverter);
        } catch (QuantityException e){
            Assert.assertEquals("Main Unit Type Should Be Same",e.getMessage());
        }
    }

    @Test
    public void givenQuantityService_When1KGAndSecondUnitAsCelsius_ShouldReturnException() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.KG, SubUnits.CELSIUS);
        try{
            quantityService.getConvertedValue(unitConverter);
        } catch (QuantityException e){
            Assert.assertEquals("Main Unit Type Should Be Same",e.getMessage());
        }
    }

    //Test For Conversion Of Volume Units
    @Test
    public void givenQuantityService_When1GallonAndSecondUnitAsLitre_ShouldReturn3Point78Litre() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.GALLON, SubUnits.LITRE);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(3.78, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When3GallonAndSecondUnitAsML_ShouldReturn11340ML() {
        UnitConverter unitConverter = new UnitConverter(3, SubUnits.GALLON, SubUnits.ML);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(11340, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When1LitreAndSecondUnitAsML_ShouldReturn1000ML() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.LITRE, SubUnits.ML);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1000, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When3Point78LitreAndSecondUnitAsGallon_ShouldReturn1Gallon() {
        UnitConverter unitConverter = new UnitConverter(3.78, SubUnits.LITRE, SubUnits.GALLON);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1, convertedValue, 0.0);
    }

    //Test For Conversion Of Weight Units
    @Test
    public void givenQuantityService_When1KGAndSecondUnitAsGrams_ShouldReturn1000Grams() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.KG, SubUnits.GRAMS);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1000, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When1TonneAndSecondUnitAsKG_ShouldReturn1000KG() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.TONNE, SubUnits.KG);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1000, convertedValue, 0.0);
    }

    //Test For Conversion Of Temperature Units
    @Test
    public void givenQuantityService_When1FahrenheitAndSecondUnitAsFahrenheit_ShouldReturn1Fahrenheit() {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FAHRENHEIT, SubUnits.FAHRENHEIT);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(1, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When212FahrenheitAndSecondUnitAsCelsius_ShouldReturn100Celsius() {
        UnitConverter unitConverter = new UnitConverter(212, SubUnits.FAHRENHEIT, SubUnits.CELSIUS);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(100, convertedValue, 0.0);
    }

    @Test
    public void givenQuantityService_When100CelsiusAndSecondUnitAsFahrenheit_ShouldReturn212Fahrenheit() {
        UnitConverter unitConverter = new UnitConverter(100, SubUnits.CELSIUS, SubUnits.FAHRENHEIT);
        double convertedValue = quantityService.getConvertedValue(unitConverter);
        Assert.assertEquals(212, convertedValue, 0.0);
    }
}