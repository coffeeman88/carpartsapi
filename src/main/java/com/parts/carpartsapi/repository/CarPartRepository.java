package com.parts.carpartsapi.repository;


import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, Long> {
List<CarPart> findCarPartByCarsBrandContainingIgnoreCaseAndCarsModelContainingIgnoreCase(String brand, String model);
List<CarPart> findDistinctByCarsBrandAndCarsModelAndCpartnameOrDescriptionContainingIgnoreCase(String brand,
                                                                                                 String model, String name, String description);
CarPart findByid(Long id);


}
