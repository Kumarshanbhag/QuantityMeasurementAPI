/*****************************************************************
 * @Purpose: To Return Mainunits,subunits And Converted Value
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/

package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.MainUnits;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {
    @Override
    public List getAllMainUnits() {
        return Arrays.asList(MainUnits.values());
    }
}
