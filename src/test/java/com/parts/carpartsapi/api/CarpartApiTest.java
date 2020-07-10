package com.parts.carpartsapi.api;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.manager.CarManager;
import com.parts.carpartsapi.manager.CarPartManager;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarpartApi.class)
class CarpartApiTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    CarPartManager carPartManager;

    CarManager carManager;

    @Test
    void testGetAll() throws Exception {
        when(carPartManager.getParts()).thenReturn(preparedCarPartsMock());
        RequestBuilder requestBuilder = get("/api/parts/all");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("Lozysko XYZ"));
    }


    @Test
    void testGetByBrand() throws Exception {
        when(carPartManager.getAllPartsForBrandAndModel(anyString(), anyString())).thenReturn(preparedCarPartsMock());
        RequestBuilder requestBuilder = get("/api/parts/brandmodel?brand=audi&model=a4");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("Lozysko XYZ"));
    }

    @Test
    void testGetByBrandDetailed() throws Exception {
        when(carPartManager.getAllPartsForBrandDetailed(anyString(), anyString(), anyString(), anyString())).thenReturn(preparedCarPartsMock());
        RequestBuilder requestBuilder = get("/api/parts/branddetailed?brand=audi&model=a4&partname=lozysko" +
                "&description=lozysko");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("Lozysko XYZ"));
    }

    @Test
    void testGetById() throws Exception {
        when(carPartManager.getById(any())).thenReturn(preparedCarPartsMock().get(0));
        RequestBuilder requestBuilder = get("/api/parts/1");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("Lozysko XYZ"));
    }


    @Test
    void testWhenWillBeShipped() throws Exception {
        this.mvc.perform(get("/api/parts/2/shipping")).
                andExpect(status().isOk()).andReturn();
        verify(carPartManager, times(1)).getShippingDaysById(2L);
    }

    @Test
    void testClearTags() throws Exception {
        this.mvc.perform(get("/api/parts/2/cleartags")).
                andExpect(status().isOk()).andReturn();
        verify(carPartManager, times(1)).clearTags(2L);
    }

    @Test
    void testChangeDescription() throws Exception {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        json.addProperty("cpartname", "Sprezyna amortyzatora ABC");
        json.addProperty("description", "Sprezyna amortyzatora ");
        json.addProperty("price", 59.99);
        json.add("tags", null);
        json.addProperty("shippingdays", 14);
        json.add("cars", null);
        String jason = gson.toJson(json);
        this.mvc.perform(put("/api/parts/2/changedescription").contentType(MediaType.APPLICATION_JSON).content(jason)).
                andExpect(status().isOk()).andReturn();
    }

    @Test
    void add() throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("actname", "Wymiana sprezynki");
        jsonObject.addProperty("servStartDate", "2020-06-01");
        jsonObject.addProperty("servEndDate", "2020-06-15");
        Gson gson = new Gson();
        String jason = gson.toJson(jsonObject);
        System.out.println(jason);
        this.mvc.perform(put("/api/parts/2/addserviceaction").contentType(MediaType.APPLICATION_JSON).content(jason)).
                andExpect(status().isOk()).andReturn();
    }

    public List<CarPart> preparedCarPartsMock() {
        ServiceAction serviceAction = new ServiceAction();
        serviceAction.setActname("Smarowanie");
        serviceAction.setServStartDate(LocalDate.of(2020, 05, 01));
        serviceAction.setServEndDate(LocalDate.of(2020, 05, 03));

        Car car = new Car();
        car.setBrand("MAZDA");
        car.setModel("6");

        CarPart carPart = new CarPart();
        carPart.setCpartname("Lozysko XYZ");
        carPart.setShippingdays(2);
        carPart.setDescription("Dobre, tanie lozysko");
        carPart.setPrice(200);
        carPart.setTags(Arrays.asList("Doskonale", "Swietne"));
        carPart.setCars(Arrays.asList(car));
        carPart.setServiceAction(Arrays.asList(serviceAction));
        carPart.setId(2L);
        car.setParts(Arrays.asList(carPart));
        serviceAction.setCarParts(carPart);

        List<CarPart> list = new ArrayList<>();
        list.addAll(Arrays.asList(carPart));
        return list;


    }
}