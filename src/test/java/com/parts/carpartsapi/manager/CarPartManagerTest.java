package com.parts.carpartsapi.manager;

import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.repository.CarPartRepository;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CarPartManagerTest {
    @Mock
    CarPartRepository carPartRepository;
    @InjectMocks
    CarPartManager carPartManager;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetParts() {
        CarPartManager carPartManager = new CarPartManager(carPartRepository);
        when(carPartRepository.findAll()).thenReturn(preparedCarPartsMock());
        List<CarPart> carParts = carPartManager.getParts();
        Assert.assertThat(carParts, Matchers.hasSize(1));
        Assert.assertThat(carParts.get(0).getCars().get(0).getModel(), Matchers.equalTo("6"));
    }

    @Test
    public void testSave() {
        CarPartManager carPartManager = new CarPartManager(carPartRepository);
        when(carPartRepository.save(any())).thenReturn(preparedCarPartsMock().get(0));
        CarPart carPart = new CarPart();
        carPart = carPartManager.save(carPart);
        Assert.assertThat(carPart.getPrice(), Matchers.equalTo(200.0));
        Assert.assertThat(carPart.getCars().get(0).getBrand(), Matchers.equalTo("MAZDA"));

    }

    @Test
    public void testGetAllPartsForBrandDetailed() {
        CarPartManager carPartManager = new CarPartManager(carPartRepository);
        when(carPartRepository.findDistinctByCarsBrandAndCarsModelAndCpartnameOrDescriptionContainingIgnoreCase(anyString(), anyString(),
                anyString(), anyString())).thenReturn(preparedCarPartsMock());
        List<CarPart> list = carPartManager.getAllPartsForBrandDetailed("Mazda", "6", "Lozysko", "Dobre");
        Assert.assertThat(list, Matchers.hasSize(1));
        Assert.assertThat(list.get(0).getShippingdays(), Matchers.equalTo(2));
    }

    @Test
    public void testGetAllPartsForBrand() {
        CarPartManager carPartManager = new CarPartManager(carPartRepository);
        when(carPartRepository.findCarPartByCarsBrandContainingIgnoreCaseAndCarsModelContainingIgnoreCase(anyString(), anyString())).thenReturn(preparedCarPartsMock());
        List<CarPart> list = carPartManager.getAllPartsForBrandAndModel("Mercedes", "W124");
        Assert.assertThat(list, Matchers.hasSize(1));
        Assert.assertThat(list.get(0).getCars().get(0).getBrand(), Matchers.equalTo("MAZDA"));
    }

    @Test
    public void testGetshippingdaysById() {
        CarPartManager carPartManager = new CarPartManager(carPartRepository);
        when(carPartRepository.findByid(any())).thenReturn(preparedCarPartsMock().get(0));
        String s = carPartManager.getShippingDaysById(2L);
        Assert.assertThat(s.contains("AVAILABLE"), Matchers.equalTo(true));
    }

    @Test
    public void testClearTags() {
        CarPartManager carPartManager = mock(CarPartManager.class);
        doNothing().when(carPartManager).clearTags(isA(Long.class));
        carPartManager.clearTags(1L);
        verify(carPartManager).clearTags(1L);

    }

    @Test
    public void testGetById() {
        CarPartManager carPartManager = new CarPartManager(carPartRepository);
        when(carPartRepository.findByid(any())).thenReturn(preparedCarPartsMock().get(0));
        CarPart carPart = carPartManager.getById(1L);
        Assert.assertThat(carPart.getCars().get(0).getModel(), Matchers.equalTo("6"));
        Assert.assertThat(carPart.getCars().get(0).getBrand(), Matchers.not("BMW"));
    }

    @Test
    public void testChangeDescription() {

        CarPartManager carPartManager = mock(CarPartManager.class);
        CarPart carPart = preparedCarPartsMock().get(0);
        doNothing().when(carPartManager).changeDescription(isA(Long.class), isA(CarPart.class));
        carPartManager.changeDescription(1L, carPart);
        verify(carPartManager, times(1)).changeDescription(1L, carPart);

    }

    @Test
    public void testAddServiceAction() throws Exception {
        CarPartManager carPartManager = mock(CarPartManager.class);
        ServiceAction serviceAction = mock(ServiceAction.class);
        doNothing().when(carPartManager).addServiceAction(isA(Long.class), isA(ServiceAction.class));
        carPartManager.addServiceAction(1L, serviceAction);
        verify(carPartManager, times(1)).addServiceAction(1L, serviceAction);

    }

    public List<CarPart> preparedCarPartsMock() {
        ServiceAction serviceAction = new ServiceAction();
        serviceAction.setActname("Smarowanie");
        serviceAction.setServStartDate(LocalDate.of(2020, 5, 1));
        serviceAction.setServEndDate(LocalDate.of(2020, 5, 3));

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
