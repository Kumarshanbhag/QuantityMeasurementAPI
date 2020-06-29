package com.quantitymeasurement.service;

import com.quantitymeasurement.model.UnitConverterDTO;

import java.util.List;

public interface IQuantityMeasurementService {
    List getAllMainUnits();

    List getAllSubUnits(String mainUnitType);

    double getConvertedValue(UnitConverterDTO unitConverterDTO);
}
