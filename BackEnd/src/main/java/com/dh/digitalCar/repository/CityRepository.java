package com.dh.digitalCar.repository;

import com.dh.digitalCar.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByNameAndCountry(String name, String Country);
}
