package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.dtos.CategoryDto;
import com.dh.digitalCar.entities.Category;

import java.util.List;
import java.util.Optional;

public interface IMainService {
    Boolean save(CategoryDto categoryDto);
    Boolean update(Integer id, CategoryDto categoryDto);
    Boolean delete(Integer id);
    Optional<Category> findById(Integer id);
    List<Category> findAll();
}
