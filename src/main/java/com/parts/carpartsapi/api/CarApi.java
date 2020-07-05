package com.parts.carpartsapi.api;

import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.manager.CarManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarApi {
    private CarManager carManager;

    @Autowired
    public CarApi(CarManager carManager) {
        this.carManager = carManager;
    }

    @GetMapping("/all")
    public List<Car> getAll() {
        return carManager.getCars();
    }


    @GetMapping("/brandmodel")
    public List<Car> getAllByBrandAndModel(@RequestParam String brand, @RequestParam String model) {

        return carManager.findByBrandAndModel(brand, model);

    }

    @GetMapping("/brand")
    public List<Car> getAllByBrand(@RequestParam String brand) {
        return carManager.findByBrand(brand);
    }

    @PutMapping("/add")
    public void save(@RequestBody Car car) {
        carManager.save(car);
    }
}
