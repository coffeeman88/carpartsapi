package com.parts.carpartsapi.api;

import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.manager.CarManager;
import com.parts.carpartsapi.manager.ServiceActionManager;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ServiceActionApi.class)
class ServiceActionApiTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    ServiceActionManager serviceActionManager;


    @Test
    void getAllSA() throws Exception {
        when(serviceActionManager.getSA()).thenReturn(preparedServiceActionsMock());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/sa/all");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("Wymiana"));

    }

    @Test
    void getByDate() throws Exception {
        when(serviceActionManager.getSAbyDate(any(),any())).thenReturn(preparedServiceActionsMock());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/sa/getbydate?start=2020-05-01&end=2020-05-03");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
        Assert.assertThat(result.getResponse().getContentAsString(), Matchers.containsString("Wymiana"));
    }

    public List<ServiceAction> preparedServiceActionsMock() {
        List<ServiceAction> list = new ArrayList<>();

        ServiceAction serviceAction = new ServiceAction();
        serviceAction.setCarParts(null);
        serviceAction.setServStartDate(LocalDate.of(2020, 05, 01));
        serviceAction.setServEndDate(LocalDate.of(2020, 05, 10));
        serviceAction.setActname("Naprawa kola");

        ServiceAction serviceAction1 = new ServiceAction();
        serviceAction1.setCarParts(null);
        serviceAction1.setServStartDate(LocalDate.of(2020, 04, 03));
        serviceAction1.setServEndDate(LocalDate.of(2020, 04, 5));
        serviceAction1.setActname("Wymiana swiec + polerowanie");

        ServiceAction serviceAction2 = new ServiceAction();
        serviceAction2.setCarParts(null);
        serviceAction2.setServStartDate(LocalDate.of(2020, 06, 10));
        serviceAction2.setServEndDate(LocalDate.of(2020, 06, 18));
        serviceAction2.setActname("Wymiana elementu");

        list.addAll(Arrays.asList(serviceAction, serviceAction1, serviceAction2));
        return list;
    }
}