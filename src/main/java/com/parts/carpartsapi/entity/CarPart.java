package com.parts.carpartsapi.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carpart")
public class CarPart implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String cpartname;
    private String description;
    private double price;
    @ElementCollection
    private List<String> tags;
    private int shippingdays;
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_carparts",
            joinColumns = {@JoinColumn(name = "car_id")},
            inverseJoinColumns = {@JoinColumn(name = "carpart_id")})
    @JsonIgnore
    private List<Car> cars;

    @OneToMany(mappedBy = "carParts", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    //@JsonIgnore
    private List<ServiceAction> serviceAction = new ArrayList<>();

    public void setSA(List<ServiceAction> sas) {
        if (this.serviceAction == null) {
            this.serviceAction = sas;
        } else {
            this.serviceAction.retainAll(sas);
            this.serviceAction.addAll(sas);
        }
    }

}
