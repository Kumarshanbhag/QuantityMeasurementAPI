/*****************************************************************
 * @Purpose: To Return Mainunits,subunits And Converted Value
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/

package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.MainUnits;
import com.quantitymeasurement.enums.SubUnits;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {
    /**
     * @Purpose: To Return All The MainUnits
     * @return Array Of List Of MainUnits
     */
    @Override
    public List getAllMainUnits() {
        return Arrays.asList(MainUnits.values());
    }

    @Override
    public List getAllSubUnits(String mainUnitType) {
        return Arrays.stream(SubUnits.values())
                .filter(subUnits -> subUnits.unitType.name().equals(mainUnitType))
                .collect(Collectors.toList());
    }
}
