package com.parts.carpartsapi.manager;

import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.repository.ServiceActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ServiceActionManager {
    ServiceActionRepository serviceActionRepository;

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
