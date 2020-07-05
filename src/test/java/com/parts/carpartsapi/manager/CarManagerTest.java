package com.parts.carpartsapi.manager;

import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.repository.CarRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarManagerTest extends CarManager {

    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarManager carManager;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCars() {

        CarManager carManager = new CarManager(carRepository);
        when(carRepository.findAll()).thenReturn(preparedMockDataCars());
        List<Car> cars = carManager.getCars();
        Assert.assertThat(cars, Matchers.hasSize(3));
        Assert.assertThat(cars.get(0).getBrand(), Matchers.equalTo("AUDI"));
    }

    @Test
    public void testFindByBrand() {
        CarManager carManager = new CarManager(carRepository);
        when(carRepository.findCarByBrandContainingIgnoreCase(anyString())).thenReturn(preparedMockDataCars());
        List<Car> cars = carManager.findByBrand("");
        Assert.assertThat(cars.get(1).getModel(), Matchers.equalTo("ASTRA H"));


    }

    @Test
    public void testFindByBrandAndModel() {
        CarManager carManager = new CarManager(carRepository);
        when(carRepository.findCarByBrandContainingIgnoreCaseAndAndModelContainingIgnoreCase(anyString(), anyString())).thenReturn(preparedMockDataCars());
        List<Car> cars = carManager.findByBrandAndModel("", "");
        Assert.assertThat(cars.get(2).getBrand(), Matchers.equalTo("AUDI"));
        Assert.assertThat(cars.get(1).getModel(), Matchers.not("POLO"));

    }

    @Test
    public void testSave() {
        CarManager carManager = new CarManager(carRepository);
        when(carRepository.save(any())).thenReturn(preparedMockDataCars().get(0));
        Car car = new Car();
        car = carManager.save(car);
        Assert.assertThat(car.getModel(), Matchers.equalTo("A4"));
        Assert.assertThat(car.getModel(), Matchers.not("BMW"));


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

