package com.quantitymeasurement.test;

import com.quantitymeasurement.controller.QuantityMeasurementController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuantityMeasurementController.class)
public class QuantityMeasurementTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/mainunit"))
        .andDo(print())
        .andExpect(status().isNotFound());
    }
}