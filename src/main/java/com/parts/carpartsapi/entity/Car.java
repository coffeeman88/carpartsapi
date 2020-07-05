package com.parts.carpartsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String brand;
    private String model;
    private LocalDate ProdStartDate;
    private LocalDate ProdEndDate;
       @JsonBackReference
    @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private List<CarPart> parts;


}
