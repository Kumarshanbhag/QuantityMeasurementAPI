/*****************************************************************
 * @Purpose: To Convert Quantity Measurement Objects
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.controller;

import com.quantitymeasurement.model.ResponseDTO;
import com.quantitymeasurement.model.UnitConverterDTO;
import com.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuantityMeasurementController {

    @Autowired
    IQuantityMeasurementService service;

    /**
     * @return Response Entity With status
     * @Purpose: To Return Response Entity On Succesfull Execution
     */
    @GetMapping ("/mainunits")
    public ResponseEntity<ResponseDTO> getAllMainUnits() {
        List allMainUnits = service.getAllMainUnits();
        return new ResponseEntity<>(new ResponseDTO(1, "Recevived Main Units", allMainUnits), HttpStatus.OK);
    }

    /**
     * @param mainUnitType
     * @return Response Entity With Status Ok
     * @Purpose: To Get All SubUnits Based On MainUnits
     */
    @GetMapping ("/subunits")
    public ResponseEntity getAllMainUnits(@RequestParam (value = "unit") String mainUnitType) {
        List allSubUnits = service.getAllSubUnits(mainUnitType);
        return new ResponseEntity(new ResponseDTO(1, "Received All SubUnits", allSubUnits), HttpStatus.OK);
    }

    /**
     * @param unitConverterDTO consists Of(Double value, 2 Subunits)
     * @return Response Entity With Ok Status
     * @Purpose: To Convert Value
     */
    @PostMapping ("/unitconvert")
    public ResponseEntity convertUnitValue(@RequestBody UnitConverterDTO unitConverterDTO) {
        double value = service.getConvertedValue(unitConverterDTO);
        return new ResponseEntity<>(new ResponseDTO(1,"Value Converted Successfully", value), HttpStatus.OK);
    }
}
