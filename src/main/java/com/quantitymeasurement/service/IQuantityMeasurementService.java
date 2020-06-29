package com.quantitymeasurement.service;

import java.util.List;

public interface IQuantityMeasurementService {
    List getAllMainUnits();

    List getAllSubUnits(String mainUnitType);
}
