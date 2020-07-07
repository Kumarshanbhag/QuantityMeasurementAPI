package com.quantitymeasurement.controller;

import com.google.gson.Gson;
import com.quantitymeasurement.enums.MainUnits;
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
        List mainUnits = Arrays.asList("LENGTH", "VOLUME", "WEIGHT", "TEMPERATURE");
        String expectedOutput = gson.toJson(new Response(200, "Recevived Main Units", mainUnits));
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
        List subUnits = Arrays.asList("FEET", "INCH", "YARD", "CM");
        String expectedOutput = gson.toJson(new Response(200, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits(MainUnits.valueOf("LENGTH"))).willReturn(subUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/subunits?unit=LENGTH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToGetSubUnits_WhenGivenVolumeAsMainUnit_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("LITRE", "GALLON", "ML");
        String expectedOutput = gson.toJson(new Response(200, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits(MainUnits.valueOf("VOLUME"))).willReturn(subUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/subunits?unit=VOLUME"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToGetSubUnits_WhenGivenWeightAsMainUnit_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("KG", "GRAMS", "TONNE");
        String expectedOutput = gson.toJson(new Response(200, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits(MainUnits.valueOf("WEIGHT"))).willReturn(subUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/subunits?unit=WEIGHT"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void givenURLToGetSubUnits_WhenGivenTemperatureAsMainUnit_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("FAHRENHEIT", "CELSIUS");
        String expectedOutput = gson.toJson(new Response(200, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits(MainUnits.valueOf("TEMPERATURE"))).willReturn(subUnits);
        MvcResult mvcResult = this.mockMvc.perform(get("/subunits?unit=TEMPERATURE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    //Test For Conversion Of Feet To Inch and vice Versa
    @Test
    public void givenURLToGetSubUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("FEET", "INCH");
        String expectedOutput = gson.toJson(new Response(200, "Received All SubUnits", subUnits));
        given(service.getAllSubUnits(MainUnits.valueOf("LENGTH"))).willReturn(subUnits);
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
        Response response = new Response(200, "Value Converted Successfully", 12.0);
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
        Response response = new Response(200, "Value Converted Successfully", 60.0);
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
        Response response = new Response(200, "Value Converted Successfully", 1.0);
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
        Response response = new Response(200, "Value Converted Successfully", 5.0);
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
        Response response = new Response(200, "Value Converted Successfully", 1.0);
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
        UnitConverter unitConverter = new UnitConverter(200, SubUnits.YARD, SubUnits.FEET);
        Response response = new Response(200, "Value Converted Successfully", 3.0);
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
        UnitConverter unitConverter = new UnitConverter(200, SubUnits.INCH, SubUnits.YARD);
        Response response = new Response(200, "Value Converted Successfully", 1.0);
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
        Response response = new Response(200, "Value Converted Successfully", 36.0);
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
        Response response = new Response(200, "Value Converted Successfully", 30.0);
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
        Response response = new Response(200, "Value Converted Successfully", 1.0);
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
        Response response = new Response(200, "Value Converted Successfully", 5.0);
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
        Response response = new Response(200, "Value Converted Successfully", 2.0);
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
        Response response = new Response(200, "Value Converted Successfully", 90.0);
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
        Response response = new Response(200, "Value Converted Successfully", 1.0);
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

    //Test For Different Main Unit Convert Values
    @Test
    public void givenURLToConvert_WhenGallonAs1AndFeetAsSecondUnit_ShouldReturnException() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.GALLON, SubUnits.FEET);
        String requestJson = gson.toJson(unitConverter);
        String expectedOutput = gson.toJson(new Response(400, "Main unit Type Should Be Same", ""));
        MvcResult mvcResult = null;
        try {
            given(service.getConvertedValue(any(UnitConverter.class))).willThrow(new QuantityException("Main unit Type Should Be Same"));
            mvcResult = this.mockMvc.perform(post("/unitconvert")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(requestJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (QuantityException e) { }
        String actualOutput = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    //Test For Conversion Of Volume Units
    @Test
    public void givenURLToConvert_WhenGallonAs1AndLitreAsSecondUnit_ShouldReturnResponseEntityWithValue3Point78() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.GALLON, SubUnits.LITRE);
        Response response = new Response(200, "Value Converted Successfully", 3.78);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(3.78);
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
    public void givenURLToConvert_WhenGallonAs3AndMLAsSecondUnit_ShouldReturnResponseEntityWithValue11340() throws Exception {
        UnitConverter unitConverter = new UnitConverter(3, SubUnits.GALLON, SubUnits.ML);
        Response response = new Response(200, "Value Converted Successfully", 11340.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(11340.0);
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
    public void givenURLToConvert_WhenLitreAs1AndMLAsSecondUnit_ShouldReturnResponseEntityWithValue1000() throws Exception {
        UnitConverter unitConverter = new UnitConverter(3, SubUnits.LITRE, SubUnits.ML);
        Response response = new Response(200, "Value Converted Successfully", 1000.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(1000.0);
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
    public void givenURLToConvert_WhenLitreAs3Point78AndGallonAsSecondUnit_ShouldReturnResponseEntityWithValue1() throws Exception {
        UnitConverter unitConverter = new UnitConverter(3.78, SubUnits.LITRE, SubUnits.GALLON);
        Response response = new Response(200, "Value Converted Successfully", 1.0);
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

    //Test For Conversion Of Weight Units
    @Test
    public void givenURLToConvert_WhenKGAs1AndGramsAsSecondUnit_ShouldReturnResponseEntityWithValue1000() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.KG, SubUnits.GRAMS);
        Response response = new Response(200, "Value Converted Successfully", 1000.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(1000.0);
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
    public void givenURLToConvert_WhenTonneAs1AndKGAsSecondUnit_ShouldReturnResponseEntityWithValue1000() throws Exception {
        UnitConverter unitConverter = new UnitConverter(1, SubUnits.TONNE, SubUnits.KG);
        Response response = new Response(200, "Value Converted Successfully", 1000.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(1000.0);
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

    //Test For Conversion Of Temperature Units
    @Test
    public void givenURLToConvert_WhenFahrenheitAs212AndCelsiusAsSecondUnit_ShouldReturnResponseEntityWithValue100() throws Exception {
        UnitConverter unitConverter = new UnitConverter(212, SubUnits.FAHRENHEIT, SubUnits.CELSIUS);
        Response response = new Response(200, "Value Converted Successfully", 100.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(100.0);
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
    public void givenURLToConvert_WhenCelsiusAs100AndFahrenheitAsSecondUnit_ShouldReturnResponseEntityWithValue212() throws Exception {
        UnitConverter unitConverter = new UnitConverter(100, SubUnits.CELSIUS, SubUnits.FAHRENHEIT);
        Response response = new Response(200, "Value Converted Successfully", 212.0);
        String requestJson = gson.toJson(unitConverter);
        given(service.getConvertedValue(any(UnitConverter.class))).willReturn(212.0);
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