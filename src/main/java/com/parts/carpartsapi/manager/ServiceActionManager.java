package com.parts.carpartsapi.manager;

import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.repository.CarPartRepository;
import com.parts.carpartsapi.repository.ServiceActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sun.deploy.util.SessionState.save;

@Service
public class ServiceActionManager {
    ServiceActionRepository serviceActionRepository;
CarPartRepository carPartRepository;

    @Autowired
    public ServiceActionManager(ServiceActionRepository serviceActionRepository) {
        this.serviceActionRepository = serviceActionRepository;
    }

    public ServiceActionManager() {
    }


    public List<ServiceAction> getSA() {
        return serviceActionRepository.findAll();
    }

    public List<ServiceAction> getSAbyDate(LocalDate start, LocalDate end) {
        return serviceActionRepository.findServiceActionByServStartDateBetween(start, end);
    }


}
