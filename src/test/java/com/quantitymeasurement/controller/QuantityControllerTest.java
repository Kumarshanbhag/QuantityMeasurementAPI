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

    //Test For Getting All Mainunits
    @Test
    public void givenURLToGetMainUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List mainUnits = Arrays.asList("LENGTH", "VOLUME");
        String expectedOutput = gson.toJson(new Response(1, "Recevived Main Units", mainUnits));
        given(service.getAllMainUnits()).willReturn(mainUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/mainunits"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    //Test for Getting All SubUnits Based On MainUnitType
    @Test
    public void givenURLToGetSubUnits_WhenGivenLengthAsMainUnit_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("FEET", "INCH", "YARD");
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
    public void givenURLToGetSubUnits_WhenGivenVolumeAsMainUnit_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("LITRE", "GALLON");
        String expectedOutput = gson.toJson(new Response(1, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits("VOLUME")).willReturn(subUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/subunits?unit=VOLUME"))
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

    //Test For Conversion Of Feet To Inch and vice Versa
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
        UnitConverter unitConverter = new UnitConverter(60, SubUnits.INCH, SubUnits.FEET);
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

    //Test For Conversion Of Yard and To Other Length Type And vice Versa
    @Test
    public void givenURLToConvert_WhenFeetAs3AndYardAsSecondUnit_ShouldReturnResponseEntityWithValue1() throws Exception {
        UnitConverter unitConverter = new UnitConverter(3, SubUnits.FEET, SubUnits.YARD);
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
    public void givenURLToConvert_WhenYardAs1AndFeetAsSecondUnit_ShouldReturnResponseEntityWithValue3() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.YARD, SubUnits.FEET);
        Response response = new Response(1, "Value Converted Successfully", 3.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(3.0);
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
    public void givenURLToConvert_WhenInchAs36AndYardAsSecondUnit_ShouldReturnResponseEntityWithValue1() throws Exception {
        UnitConverter unitConverter = new UnitConverter(36, SubUnits.INCH, SubUnits.YARD);
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
    public void givenURLToConvert_WhenYardAs1AndInchAsSecondUnit_ShouldReturnResponseEntityWithValue36() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.YARD, SubUnits.INCH);
        Response response = new Response(1, "Value Converted Successfully", 36.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(36.0);
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

    //Test For Conversion Of Centimeter and To Other Length Type And vice Versa
    @Test
    public void givenURLToConvert_WhenFeetAs1AndCentimeterAsSecondUnit_ShouldReturnResponseEntityWithValue30() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.FEET, SubUnits.CM);
        Response response = new Response(1, "Value Converted Successfully", 30.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(30.0);
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
    public void givenURLToConvert_WhenCentimeterAs30AndFeetAsSecondUnit_ShouldReturnResponseEntityWithValue1() throws Exception {
        UnitConverter unitConverter = new UnitConverter(30, SubUnits.CM, SubUnits.FEET);
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
    public void givenURLToConvert_WhenInchAs2AndCentimeterAsSecondUnit_ShouldReturnResponseEntityWithValue5() throws Exception {
        UnitConverter unitConverter = new UnitConverter(2, SubUnits.INCH, SubUnits.CM);
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

    @Test
    public void givenURLToConvert_WhenCentimeterAs5AndInchAsSecondUnit_ShouldReturnResponseEntityWithValue2() throws Exception {
        UnitConverter unitConverter = new UnitConverter(5, SubUnits.CM, SubUnits.INCH);
        Response response = new Response(1, "Value Converted Successfully", 2.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(2.0);
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
    public void givenURLToConvert_WhenYardAs1AndCentimeterAsSecondUnit_ShouldReturnResponseEntityWithValue90() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.YARD, SubUnits.CM);
        Response response = new Response(1, "Value Converted Successfully", 90.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(90.0);
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
    public void givenURLToConvert_WhenCentimeterAs90AndInchAsSecondUnit_ShouldReturnResponseEntityWithValue1() throws Exception {
        UnitConverter unitConverter = new UnitConverter(90, SubUnits.CM, SubUnits.YARD);
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
}