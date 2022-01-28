package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.dtos.CarDto;
import com.dh.digitalCar.entities.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICarService {
    Boolean save(Car car);
    List<Car> findAll();
    Optional<Car> findByID(Integer id);
    Optional<CarDto> findByIdDto(Integer id);
    List<Car> findAllByCity(String cityName);
    List<Car> findAllByCategory(String categoryName);
    List<Car> findAllByCityAndCategory(String cityName, String categoryName);
    List<CarDto> findAllByDate(LocalDate startDate, LocalDate endDate);
    List<CarDto> findAllByDateAndCity(LocalDate startDate, LocalDate endDate, String city);
    Boolean update(Car car);
}
