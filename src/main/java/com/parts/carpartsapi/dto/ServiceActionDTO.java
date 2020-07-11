package com.parts.carpartsapi.dto;

import com.parts.carpartsapi.entity.CarPart;

import java.time.LocalDate;

public class ServiceActionDTO {
    private Long id;
    private String actname;
    private LocalDate servStartDate;
    private LocalDate servEndDate;
    private CarPart carParts;
}
