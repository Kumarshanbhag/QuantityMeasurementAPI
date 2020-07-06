/*****************************************************************
 * @Purpose: To Return Mainunits,subunits And Converted Value
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/

package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.MainUnits;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.exception.QuantityException;
import com.quantitymeasurement.model.UnitConverter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityService implements IQuantityService {
    /**
     * @Purpose: To Return All The MainUnits
     * @return List Of MainUnits
     */
    @Override
    public List getAllMainUnits() {
        return Arrays.asList(MainUnits.values());
    }

    /**
     * @Purpose: To Return All SubUnits Based On Given MainUnit Type
     * @param mainUnitType
     * @return List Of SubUnits
     */
    @Override
    public List getAllSubUnits(MainUnits mainUnitType) {
        List<SubUnits> subUnitsList = Arrays.stream(SubUnits.values())
                .filter(subUnits -> subUnits.unitType.equals(mainUnitType))
                .collect(Collectors.toList());
        return subUnitsList;
    }

    /*
     * @Purpose: To Convert And Return Converted Value Based On Given 2 Subunit And Its Value
     * @param converter
     * @return Double Value If Both SubUnits Have Same MainUnit Type Or Return 0.0
     */
    @Override
    public double getConvertedValue(UnitConverter converter) {
        if (converter.firstUnitType.unitType.equals(MainUnits.TEMPERATURE)) {
            return conversionForTemperatureUnits(converter);
        }
        else if (converter.firstUnitType.unitType.equals(converter.secondUnitType.unitType)) {
            return (converter.value * converter.firstUnitType.conversionValue) / converter.secondUnitType.conversionValue;
        }
        throw new QuantityException("Main Unit Type Should Be Same");
    }

    private double conversionForTemperatureUnits(UnitConverter converter) {
        if (converter.firstUnitType.equals(SubUnits.CELSIUS) && converter.secondUnitType.equals(SubUnits.FAHRENHEIT)) {
            return ((converter.value * converter.firstUnitType.conversionValue) + 32);
        }
        if (converter.firstUnitType.equals(SubUnits.FAHRENHEIT) && converter.secondUnitType.equals(SubUnits.CELSIUS)) {
            return ((converter.value - 32) * converter.firstUnitType.conversionValue);
        }
        throw new QuantityException("Main Unit Type Should Be Same");
    }
}
