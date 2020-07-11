package com.parts.carpartsapi.api;

import com.parts.carpartsapi.dto.CarDTO;
import com.parts.carpartsapi.dto.CarPartDTO;
import com.parts.carpartsapi.dto.ServiceActionDTO;
import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.manager.CarPartManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parts")
public class CarpartApi {
    private CarPartManager carPartManager;
    private ModelMapper modelMapper;

    @Autowired
    public CarpartApi(CarPartManager carPartManager, ModelMapper modelMapper) {
        this.carPartManager = carPartManager;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<CarPartDTO> getAll() {
        List<CarPart> list = carPartManager.getParts();
        return list.stream()
                .map(this::convertCarPartToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/brandmodel")
    public List<CarPartDTO> getByBrandAndModel(@RequestParam String brand, @RequestParam String model) {
        List<CarPart> list = carPartManager.getAllPartsForBrandAndModel(brand, model);
        return list.stream()
                .map(this::convertCarPartToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/branddetailed")
    public List<CarPartDTO> getByBrandDetailed(@RequestParam String brand, @RequestParam String model,
                                               @RequestParam String partname, @RequestParam String description) {
        List<CarPart> list = carPartManager.getAllPartsForBrandDetailed(brand, model, partname, description);
        return list.stream()
                .map(this::convertCarPartToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarPartDTO getById(@PathVariable Long id) {
        CarPart carPart = carPartManager.getById(id);
        return convertCarPartToDTO(carPart);
    }

    @GetMapping("/{id}/shipping")
    public String whenWillBeShipped(@PathVariable Long id) {
        return carPartManager.getShippingDaysById(id);
    }

    @GetMapping("/{id}/cleartags")
    public void clearTags(@PathVariable Long id) {
        carPartManager.clearTags(id);
    }

    @PutMapping("/{id}/changedescription")
    public void changeDescription(@PathVariable Long id, @RequestBody CarPartDTO carPartDTO) {
        CarPart carPart = convertDTOToCarPart(carPartDTO);
        carPartManager.changeDescription(id, carPart);
    }

    @PutMapping(value = "/{id}/addserviceaction")
    public void addServiceAction(@PathVariable Long id, @RequestBody ServiceActionDTO serviceActionDTO) {
        ServiceAction serviceAction = convertDTOToServiceAction(serviceActionDTO);
        carPartManager.addServiceAction(id, serviceAction);
    }

    private CarPartDTO convertCarPartToDTO(CarPart carPart) {
        CarPartDTO carPartDTO = modelMapper.map(carPart, CarPartDTO.class);
        return carPartDTO;
    }

    private CarPart convertDTOToCarPart(CarPartDTO carPartDTO) {
        CarPart carPart = modelMapper.map(carPartDTO, CarPart.class);
        return carPart;
    }

    private ServiceAction convertDTOToServiceAction (ServiceActionDTO serviceActionDTO){
        ServiceAction serviceAction = modelMapper.map(serviceActionDTO, ServiceAction.class);
        return serviceAction;
    }

}


