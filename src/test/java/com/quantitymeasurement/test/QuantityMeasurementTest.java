package com.quantitymeasurement.test;

import com.google.gson.Gson;
import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.model.UnitConverterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (QuantityMeasurementController.class)
public class QuantityMeasurementTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenURLToGetMainUnits_WhenProper_ShouldReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/mainunit"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenURLToGetSubUnits_WhenProper_ShouldReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/subunit/LENGTH"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenURLToConvert_WhenProper_ShouldReturnOkStatus() throws Exception {
        Gson gson = new Gson();
        UnitConverterDTO unitConverterDTO = new UnitConverterDTO(1, "Feet", "Inch");
        String requestJson = gson.toJson(unitConverterDTO);
        this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}