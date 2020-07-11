package com.parts.carpartsapi.api;

import com.parts.carpartsapi.dto.CarDTO;
import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.manager.CarManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
public class CarApi {
    private CarManager carManager;
    private final ModelMapper modelMapper;

    @Autowired
    public CarApi(CarManager carManager, ModelMapper modelMapper) {
        this.carManager = carManager;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<CarDTO> getAll() {
        List<Car> getCars = carManager.getCars();
        return getCars.stream()
                .map(this::convertCarToDTO)
                .collect(Collectors.toList());
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

    private CarDTO convertCarToDTO(Car car) {
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return carDTO;
    }

}
