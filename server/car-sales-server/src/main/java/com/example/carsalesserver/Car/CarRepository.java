package com.example.carsalesserver.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository
        extends JpaRepository<Car, Long> {

//    @Query("SELECT c FROM Car c WHERE c.manufacturer = ?1")
    Optional<Car> findCarByManufacturer(String Manufacturer);

    @Query("SELECT DISTINCT manufacturer FROM Car")
    List<String> findDistinctManufacturers();

    @Query("SELECT DISTINCT model FROM Car c WHERE c.manufacturer = ?1")
    List<String> findDistinctModelsByManufacturers(String manufacturer);


}
