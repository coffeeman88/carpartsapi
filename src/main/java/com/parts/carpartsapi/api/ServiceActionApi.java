package com.parts.carpartsapi.api;

import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.manager.ServiceActionManager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sa")
public class ServiceActionApi {
    private ServiceActionManager serviceActionManager;

    public ServiceActionApi(ServiceActionManager serviceActionManager) {
        this.serviceActionManager = serviceActionManager;
    }

    @GetMapping("/all")
    public List<ServiceAction> getAll() {
        return serviceActionManager.getSA();
    }

    @GetMapping("/getbydate")
    public List<ServiceAction> getByDate(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                         @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return serviceActionManager.getSAbyDate(start, end);
    }



}
