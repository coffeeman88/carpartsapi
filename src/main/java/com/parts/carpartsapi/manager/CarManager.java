package com.parts.carpartsapi.manager;

import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarManager {
    private CarRepository carRepository;

    @Autowired
    public CarManager(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarManager() {
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }


    public List<Car> findByBrandAndModel(String brand, String model) {
        return carRepository.findCarByBrandContainingIgnoreCaseAndAndModelContainingIgnoreCase(brand, model);
    }

    public List<Car> findByBrand(String brand) {
        return carRepository.findCarByBrandContainingIgnoreCase(brand);
    }
    public Car save(Car car) {

        return carRepository.save(car);
    }
}
