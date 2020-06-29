package com.quantitymeasurement.integrationtest;

import com.google.gson.Gson;
import com.quantitymeasurement.QuantitymeasurementApplication;
import com.quantitymeasurement.model.ResponseDTO;
import com.quantitymeasurement.model.UnitConverterDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith (SpringRunner.class)
@SpringBootTest (classes = QuantitymeasurementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuantityMeasurementIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testTemplate;

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    Gson gson = new Gson();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void givenURLToGetMainUnits_WhenProper_ShouldReturnOkStatus() throws Exception {
        ResponseEntity<String> response = testTemplate.exchange(
                createURLWithPort("/mainunits"),
                HttpMethod.GET, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenURLToGetSubUnits_WhenProper_ShouldReturnOkStatus() throws Exception {
        ResponseEntity<String> response = testTemplate.exchange(
                createURLWithPort("/subunits?unit=LENGTH"),
                HttpMethod.GET, entity, String.class);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void givenURLToConvert_WhenProper_ShouldReturnOkStatus() throws Exception {
        UnitConverterDTO unitConverterDTO = new UnitConverterDTO(1, "Feet", "Inch");
        String requestJson = gson.toJson(unitConverterDTO);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = testTemplate.exchange(
                createURLWithPort("/unitconvert"),
                HttpMethod.POST, entity, String.class);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void givenURLToGetMainUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List mainUnits = Arrays.asList("LENGTH");
        ResponseEntity<String> response = testTemplate.exchange(
                createURLWithPort("/mainunits"),
                HttpMethod.GET, entity, String.class);
        String expected = gson.toJson(new ResponseDTO(1, "Recevived Main Units", mainUnits));
        Assert.assertEquals(expected, response.getBody());
    }

    @Test
    public void givenURLToGetSubUnits_WhenProper_ShouldReturnResponseEntity() throws Exception {
        List subUnits = Arrays.asList("FEET","INCH");
        String expected = gson.toJson(new ResponseDTO(1, "Received All SubUnits", subUnits));
        ResponseEntity<String> response = testTemplate.exchange(
                createURLWithPort("/subunits?unit=LENGTH"),
                HttpMethod.GET, entity, String.class);
        Assert.assertEquals(expected, response.getBody());
    }
}