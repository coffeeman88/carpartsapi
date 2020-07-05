package com.parts.carpartsapi.repository;
import com.parts.carpartsapi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository <Car, Long> {
    List<Car> findCarByBrandContainingIgnoreCaseAndAndModelContainingIgnoreCase(String brand, String model);
    List<Car> findCarByBrandContainingIgnoreCase(String brand);

}
