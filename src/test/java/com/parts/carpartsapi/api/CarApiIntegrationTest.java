package com.parts.carpartsapi.api;

import com.google.gson.Gson;
import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.manager.CarManager;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarApi.class)
class CarApiIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CarManager carManager;

    @Test
    void getAll() throws Exception {
        when(carManager.getCars()).thenReturn(preparedMockDataCars());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars/all?start=2020-05-01&end=2020-05-02");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("AUDI"));
    }

    @Test
    void getAllByBrandAndModel() throws Exception {
        when(carManager.findByBrandAndModel(anyString(), anyString())).thenReturn(preparedMockDataCars());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars/brandmodel?brand=AUDI&model=A4");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("A6"));
    }

    @Test
    void getAllByBrand() throws Exception {
        when(carManager.findByBrand(anyString())).thenReturn(preparedMockDataCars());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars/brand?brand=AUDI");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("OPEL"));
    }

    @Test
    void save() throws Exception {
        Car car = preparedMockDataCars().get(0);
        Gson gson = new Gson();
        String json = gson.toJson(car);
        when(carManager.save(any())).thenReturn(preparedMockDataCars().get(0));
        this.mvc.perform(put("/api/cars/add").contentType(MediaType.APPLICATION_JSON).content(json)).
                andExpect(status().isOk()).andReturn();
    }

    private List<Car> preparedMockDataCars() {
        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setBrand("AUDI");
        car.setModel("A4");
        car.setProdStartDate(LocalDate.of(2000, 01, 01));
        car.setProdEndDate(LocalDate.of(2010, 01, 01));
        car.setParts(null);
        Car car1 = new Car();
        car1.setBrand("OPEL");
        car1.setModel("ASTRA H");
        car1.setProdStartDate(LocalDate.of(2000, 01, 01));
        car1.setProdEndDate(LocalDate.of(2007, 05, 01));
        car1.setParts(null);
        Car car2 = new Car();
        car2.setBrand("AUDI");
        car2.setModel("A6");
        car2.setProdStartDate(LocalDate.of(1999, 01, 01));
        car2.setProdEndDate(LocalDate.of(2004, 05, 01));
        car2.setParts(null);
        cars.add(car);
        cars.add(car1);
        cars.add(car2);
        return cars;
    }
}