package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.MainUnits;
import com.quantitymeasurement.model.UnitConverter;

import java.util.List;

public interface IQuantityService {
    List getAllMainUnits();

    List getAllSubUnits(MainUnits mainUnitType);

    double getConvertedValue(UnitConverter unitConverter);
}
