package com.parts.carpartsapi.manager;

import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.repository.ServiceActionRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class ServiceActionManagerTest {

    @Mock
    ServiceActionRepository serviceActionRepository;
    @InjectMocks
    ServiceActionManager serviceActionManager;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSA() {
        ServiceActionManager serviceActionManager = new ServiceActionManager(serviceActionRepository);
        when(serviceActionRepository.findAll()).thenReturn(preparedServiceActionsMock());
        List<ServiceAction> list = serviceActionManager.getSA();
        Assert.assertThat(list, Matchers.hasSize(3));
        Assert.assertThat(list.get(0).getServStartDate(), Matchers.equalTo(LocalDate.of(2020, 05, 01)));
    }

    @Test
    public void testGetSAbyDate() {
        ServiceActionManager serviceActionManager = new ServiceActionManager(serviceActionRepository);
        when(serviceActionRepository.findServiceActionByServStartDateBetween(LocalDate.now(), LocalDate.now())).thenReturn(preparedServiceActionsMock());
        List<ServiceAction> list = serviceActionManager.getSAbyDate(LocalDate.now(), LocalDate.now());
        Assert.assertThat(list, Matchers.hasSize(3));
        Assert.assertThat(list.get(2).getActname(), Matchers.equalTo("Wymiana elementu"));
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