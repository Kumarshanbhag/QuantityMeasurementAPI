/*****************************************************************
 * @Purpose: To Convert Quantity Measurement Objects
 * @Author: Kumar Shanbhag
 * @Date: 18/05/2020
 ****************************************************************/
package com.quantitymeasurement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuantityMeasurementController {
    /**
     * @Purpose: To Return Response Entity On Succesfull Execution
     * @return Response Entity With status
     */
    @GetMapping ("/mainunit")
    public ResponseEntity getAllMainUnits() {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Purpose: To Get All SubUnits Based On MainUnits
     * @param mainUnitType
     * @return Response Entity With Status Ok
     */
    @GetMapping ("/subunit/{mainUnitType}")
    public ResponseEntity getAllMainUnits(@PathVariable String mainUnitType) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
