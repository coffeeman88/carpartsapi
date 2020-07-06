package com.parts.carpartsapi.manager;

import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.repository.CarPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarPartManager {
    private CarPartRepository carPartRepository;

    public static LocalDateTime addDaysSkippingWeekends(LocalDateTime date, int days) {
        LocalDateTime result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }

    @Autowired
    public CarPartManager(CarPartRepository carPartRepository) {
        this.carPartRepository = carPartRepository;
    }


    public List<CarPart> getParts() {

        return carPartRepository.findAll();
    }

    public CarPart save(CarPart carPart) {

        return carPartRepository.save(carPart);
    }

    public List<CarPart> getAllPartsForBrandDetailed(String brand, String model, String name, String description) {
        return carPartRepository.findDistinctByCarsBrandAndCarsModelAndCpartnameOrDescriptionContainingIgnoreCase(
                brand, model, name, description);
    }

    public List<CarPart> getAllPartsForBrandAndModel(String brand, String model) {
        return carPartRepository.findCarPartByCarsBrandContainingIgnoreCaseAndCarsModelContainingIgnoreCase(brand, model);
    }


    public String getShippingDaysById(Long id) {

        CarPart carPart = carPartRepository.findByid(id);
        if (carPart != null) {
            if (id % 2 == 0)
                return "Part is AVAILABLE, the next shipping date is: " + addDaysSkippingWeekends(LocalDateTime.now(),
                        carPart.getShippingdays()).toString();
            else
                return "The part is currently UNAVAILABLE.";
        } else
            return "WARNING! Haven't found part with id: " + id;

    }

    public void clearTags(Long id) {

        CarPart carPart = carPartRepository.findByid(id);

        List<String> l = carPart.getTags();
        l.clear();
        carPart.setTags(l);
        save(carPart);
    }

    public CarPart getById(Long id) {


        return carPartRepository.findByid(id);


    }

    public void changeDescription(Long id, CarPart newCarPart) {
        CarPart carPart = carPartRepository.findByid(id);
        carPart.setDescription(newCarPart.getDescription());
        save(carPart);
    }

    public void addServiceAction(Long id, ServiceAction serviceAction) throws Exception {
        CarPart carPartupdate = carPartRepository.findByid(id);
       if (carPartupdate!=null)
           try {
               List<ServiceAction> list = new ArrayList();
               list.add(serviceAction);
               carPartupdate.getServiceAction().addAll(list);
               serviceAction.setCarParts(carPartupdate);
               save(carPartupdate);
           }
       catch (Exception e){
               e.printStackTrace();
       }

    }


}
