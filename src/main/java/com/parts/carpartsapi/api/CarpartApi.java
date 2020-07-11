package com.parts.carpartsapi.api;

import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.manager.CarPartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class CarpartApi {
    private CarPartManager carPartManager;

    @Autowired
    public CarpartApi(CarPartManager carPartManager) {
        this.carPartManager = carPartManager;
    }

    @GetMapping("/all")
    public List<CarPart> getAll() {

            return carPartManager.getParts();

    }




   @GetMapping("/brandmodel")
    public List<CarPart> getByBrandAndModel(@RequestParam String brand, @RequestParam String model) {
        return carPartManager.getAllPartsForBrandAndModel(brand, model);
    }

    @GetMapping("/branddetailed")
    public List<CarPart> getByBrandDetailed(@RequestParam String brand, @RequestParam String model,
                                            @RequestParam String partname, @RequestParam String description) {
        return carPartManager.getAllPartsForBrandDetailed(brand, model, partname, description);
    }

    @GetMapping("/{id}")
    public CarPart getById(@PathVariable Long id) {
        return carPartManager.getById(id);
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
    public void changeDescription(@PathVariable Long id, @RequestBody CarPart carPart) {
        carPartManager.changeDescription(id, carPart);
    }

    @PutMapping(value = "/{id}/addserviceaction")
    public void addServiceAction(@PathVariable Long id, @RequestBody ServiceAction serviceAction) throws Exception {
        carPartManager.addServiceAction(id, serviceAction);
    }


}


