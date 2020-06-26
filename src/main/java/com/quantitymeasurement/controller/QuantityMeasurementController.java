/*****************************************************************
 * @Purpose: To Convert Quantity Measurement Objects
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.controller;

import com.quantitymeasurement.model.UnitConverterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
