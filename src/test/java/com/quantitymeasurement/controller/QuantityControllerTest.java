package com.quantitymeasurement.controller;

import com.google.gson.Gson;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.exception.QuantityException;
import com.quantitymeasurement.model.Response;
import com.quantitymeasurement.model.UnitConverter;
import com.quantitymeasurement.service.IQuantityService;
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

@WebMvcTest (QuantityController.class)
public class QuantityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IQuantityService service;

    Gson gson = new Gson();

    @Test
    public void givenURLToGetMainUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List mainUnits = Arrays.asList("LENGTH");
        String expectedOutput = gson.toJson(new Response(1, "Recevived Main Units", mainUnits));
        given(service.getAllMainUnits()).willReturn(mainUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/mainunits"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToGetSubUnits_WhenGivenLengthAsMainUnit_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("FEET", "INCH");
        String expectedOutput = gson.toJson(new Response(1, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits("LENGTH")).willReturn(subUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/subunits?unit=LENGTH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToGetSubUnits_WhenNotCorrect_ShouldThrowException() throws Exception {
        try {
            given(service.getAllSubUnits("MAINUNIT")).willThrow(new QuantityException("No Main Type Found"));
            this.mockMvc.perform(get("/subunits?unit=MAINUNIT"))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        } catch (QuantityException e) {
            Assert.assertEquals("No Main Type Found", e.getMessage());
        }
    }

    @Test
    public void givenURLToGetSubUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("FEET", "INCH");
        String expectedOutput = gson.toJson(new Response(1, "Received All SubUnits", subUnits));
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
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FEET, SubUnits.INCH);
        Response response = new Response(1, "Value Converted Successfully", 12.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(12.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        String expectedOutput = gson.toJson(response);
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToConvert_WhenFeetAs5AndInchAsSecondUnit_ShouldReturnResponseEntityWithValue60() throws Exception {
        UnitConverter unitConverter = new UnitConverter(5, SubUnits.FEET, SubUnits.INCH);
        Response response = new Response(1, "Value Converted Successfully", 60.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(60.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        String expectedOutput = gson.toJson(response);
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToConvert_WhenInchAs12AndFeetAsSecondUnit_ShouldReturnResponseEntityWithValue1() throws Exception {
        UnitConverter unitConverter = new UnitConverter(12, SubUnits.INCH, SubUnits.FEET);
        Response response = new Response(1, "Value Converted Successfully", 1.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(1.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        String expectedOutput = gson.toJson(response);
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToConvert_WhenInchAs60AndFeetAsSecondUnit_ShouldReturnResponseEntityWithValue5() throws Exception {
        UnitConverter unitConverter = new UnitConverter(60, SubUnits.FEET, SubUnits.INCH);
        Response response = new Response(1, "Value Converted Successfully", 5.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(5.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitconvert")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        String expectedOutput = gson.toJson(response);
        Assert.assertEquals(actualOutput, expectedOutput);
    }
}