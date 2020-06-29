/*****************************************************************
 * @Purpose: To Return Mainunits,subunits And Converted Value
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/

package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.MainUnits;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.model.UnitConverterDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {
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
    public List getAllSubUnits(String mainUnitType) {
        return Arrays.stream(SubUnits.values())
                .filter(subUnits -> subUnits.unitType.name().equals(mainUnitType))
                .collect(Collectors.toList());
    }

    /*
     * @Purpose: To Convert And Return Converted Value Based On Given 2 Subunit And Its Value
     * @param converterDTO
     * @return Double Value If Both SubUnits Have Same MainUnit Type Or Return 0.0
     */
    @Override
    public double getConvertedValue(UnitConverterDTO converterDTO) {
        if (converterDTO.firstUnitType.unitType.equals(converterDTO.secondUnitType.unitType)) {
            return (converterDTO.value * converterDTO.firstUnitType.conversionValue) / converterDTO.secondUnitType.conversionValue;
        }
        return 0.0;
    }
}
