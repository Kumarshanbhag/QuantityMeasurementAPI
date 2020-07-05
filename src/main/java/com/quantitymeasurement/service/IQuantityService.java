package com.quantitymeasurement.service;

import com.quantitymeasurement.model.UnitConverter;

import java.util.List;

public interface IQuantityService {
    List getAllMainUnits();

    List getAllSubUnits(String mainUnitType);

    double getConvertedValue(UnitConverter unitConverter);
}
