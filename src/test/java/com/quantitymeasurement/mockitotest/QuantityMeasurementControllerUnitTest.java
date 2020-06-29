package com.quantitymeasurement.mockitotest;

import com.google.gson.Gson;
import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.model.ResponseDTO;
import com.quantitymeasurement.model.UnitConverterDTO;
import com.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (QuantityMeasurementController.class)
public class QuantityMeasurementControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IQuantityMeasurementService service;

    Gson gson = new Gson();

    @Test
    public void givenURLToGetMainUnits_WhenProper_ShouldReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/mainunits"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenURLToGetSubUnits_WhenProper_ShouldReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/subunits?unit=LENGTH"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenURLToConvert_WhenProper_ShouldReturnOkStatus() throws Exception {
        UnitConverterDTO unitConverterDTO = new UnitConverterDTO(1, SubUnits.FEET, SubUnits.INCH);
        String requestJson = gson.toJson(unitConverterDTO);
        this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenURLToGetMainUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List mainUnits = Arrays.asList("LENGTH");
        String expectedOutput = gson.toJson(new ResponseDTO(1, "Recevived Main Units", mainUnits));
        given(service.getAllMainUnits()).willReturn(mainUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/mainunits"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToGetSubUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("FEET","INCH");
        String expectedOutput = gson.toJson(new ResponseDTO(1, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits("LENGTH")).willReturn(subUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/subunits?unit=LENGTH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToConvert_WhenFeetAs1AndInchAsSecondUnit_ShouldReturnResponseEntityWithValue12() throws Exception {
        UnitConverterDTO unitConverterDTO = new UnitConverterDTO(1, SubUnits.FEET, SubUnits.INCH);
        ResponseDTO responseDTO = new ResponseDTO(1, "Value Converted Successfully", 12.0);
        String requestJson = gson.toJson(unitConverterDTO);
        given(service.getConvertedValue(any(UnitConverterDTO.class))).willReturn(12.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        String expectedOutput = gson.toJson(responseDTO);
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToConvert_WhenFeetAs5AndInchAsSecondUnit_ShouldReturnResponseEntityWithValue60() throws Exception {
        UnitConverterDTO unitConverterDTO = new UnitConverterDTO(5, SubUnits.FEET, SubUnits.INCH);
        ResponseDTO responseDTO = new ResponseDTO(1, "Value Converted Successfully", 60.0);
        String requestJson = gson.toJson(unitConverterDTO);
        given(service.getConvertedValue(any(UnitConverterDTO.class))).willReturn(60.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        String expectedOutput = gson.toJson(responseDTO);
        Assert.assertEquals(actualOutput, expectedOutput);
    }
}