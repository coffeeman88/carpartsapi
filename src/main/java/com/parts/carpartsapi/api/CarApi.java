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
        List<Car> list = carManager.getCars();
        return list.stream()
                .map(this::convertCarToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/brandmodel")
    public List<CarDTO> getAllByBrandAndModel(@RequestParam String brand, @RequestParam String model) {
        List<Car> list = carManager.findByBrandAndModel(brand, model);
        return list.stream()
                .map(this::convertCarToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/brand")
    public List<CarDTO> getAllByBrand(@RequestParam String brand) {
        List<Car> list = carManager.findByBrand(brand);
        return list.stream()
                .map(this::convertCarToDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/add")
    public void save(@RequestBody CarDTO carDTO) {
        Car car = convertDTOToCar(carDTO);
        carManager.save(car);
    }

    private CarDTO convertCarToDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    private Car convertDTOToCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }
}
