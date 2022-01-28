package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.entities.City;

import java.util.List;
import java.util.Optional;

public interface ICityService {
    Boolean save(City city);
    List<City> findAll();
    Optional<City> findById(Integer id);
}
