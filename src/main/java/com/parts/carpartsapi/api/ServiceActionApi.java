package com.parts.carpartsapi.api;

import com.parts.carpartsapi.dto.ServiceActionDTO;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.manager.ServiceActionManager;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/serviceaction")
public class ServiceActionApi {
    private ServiceActionManager serviceActionManager;
    private ModelMapper modelMapper;

    public ServiceActionApi(ServiceActionManager serviceActionManager, ModelMapper modelMapper) {
        this.serviceActionManager = serviceActionManager;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<ServiceActionDTO> getAll() {
        List<ServiceAction> list = serviceActionManager.getServiceActions();
        return list.stream()
                .map(this::convertServiceActionToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/getbydate")
    public List<ServiceActionDTO> getByDate(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                            @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<ServiceAction> list = serviceActionManager.getServiceActionsByDate(start, end);
        return list.stream()
                .map(this::convertServiceActionToDTO)
                .collect(Collectors.toList());
    }

    private ServiceActionDTO convertServiceActionToDTO(ServiceAction serviceAction) {
        return modelMapper.map(serviceAction, ServiceActionDTO.class);
    }
}