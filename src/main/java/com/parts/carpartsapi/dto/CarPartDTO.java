package com.parts.carpartsapi.dto;

import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.ServiceAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class CarPartDTO {
    private Long id;
    private String cpartname;
    private String description;
    private double price;
    private List<String> tags;
    private int shippingdays;
    private List<Car> cars;
    private List<ServiceAction> serviceAction = new ArrayList<>();
}
