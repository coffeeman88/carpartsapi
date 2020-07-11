package com.parts.carpartsapi.dto;

import com.parts.carpartsapi.entity.CarPart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO {
    private Long id;
    public String brand;
    private String model;
    private LocalDate ProdStartDate;
    private LocalDate ProdEndDate;
    private List<CarPart> parts;
}
