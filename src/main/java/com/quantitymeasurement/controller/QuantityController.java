/*****************************************************************
 * @Purpose: To Convert Quantity Measurement Objects
 * @Author: Kumar Shanbhag
 * @Date: 27/06/2020
 ****************************************************************/
package com.quantitymeasurement.controller;

import com.quantitymeasurement.enums.MainUnits;
import com.quantitymeasurement.model.Response;
import com.quantitymeasurement.model.UnitConverter;
import com.quantitymeasurement.service.IQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuantityController {

    @Autowired
    IQuantityService service;

    /**
     * @return Response Entity With status
     * @Purpose: To Return Response Entity On Succesfull Execution
     */
    @GetMapping ("/mainunits")
    public ResponseEntity<Response> getAllMainUnits() {
        List allMainUnits = service.getAllMainUnits();
        return new ResponseEntity<>(new Response(1, "Recevived Main Units", allMainUnits), HttpStatus.OK);
    }

    /**
     * @param mainUnitType
     * @return Response Entity With Status Ok
     * @Purpose: To Get All SubUnits Based On MainUnits
     */
    @GetMapping ("/subunits")
    public ResponseEntity getAllMainUnits(@RequestParam (value = "unit") MainUnits mainUnitType) {
        List allSubUnits = service.getAllSubUnits(mainUnitType);
        return new ResponseEntity(new Response(1, "Received All SubUnits", allSubUnits), HttpStatus.OK);
    }

    /**
     * @param unitConverter consists Of(Double value, 2 Subunits)
     * @return Response Entity With Ok Status
     * @Purpose: To Convert Value
     */
    @PostMapping ("/unitconvert")
    public ResponseEntity convertUnitValue(@RequestBody UnitConverter unitConverter) {
        double value = service.getConvertedValue(unitConverter);
        return new ResponseEntity<>(new Response(1,"Value Converted Successfully", value), HttpStatus.OK);
    }
}
