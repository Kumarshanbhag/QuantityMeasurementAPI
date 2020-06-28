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
     * @Purpose: To Return Response Entity On Succesfull Execution
     * @return Response Entity With status
     */
    @GetMapping ("/mainunits")
    public ResponseEntity<ResponseDTO> getAllMainUnits() {
        List allMainUnits = service.getAllMainUnits();
        return new ResponseEntity<>(new ResponseDTO(1, "Recevived Main Units", allMainUnits), HttpStatus.OK);
    }

    /**
     * @Purpose: To Get All SubUnits Based On MainUnits
     * @param mainUnitType
     * @return Response Entity With Status Ok
     */
    @GetMapping ("/subunits/{mainUnitType}")
    public ResponseEntity getAllMainUnits(@PathVariable String mainUnitType) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Purpose: To Convert Value
     * @param unitConverterDTO consists Of(Double value, 2 Subunits)
     * @return Response Entity With Ok Status
     */
    @PostMapping ("/unitconvert")
    public ResponseEntity convertUnitValue(@RequestBody UnitConverterDTO unitConverterDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
