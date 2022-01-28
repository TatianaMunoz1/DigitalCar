package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.CategoryDto;
import com.dh.digitalCar.entities.Category;
import com.dh.digitalCar.services.servicesInterfaces.ICategoryService;
import com.dh.digitalCar.services.servicesInterfaces.IMainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainService implements IMainService {
    private ICategoryService categoryService;

    public MainService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Boolean save(CategoryDto categoryDto) {
        //se pueden repetir?
//        categoryService.save(categoryDto.getCategory());
        return true;
    }

    @Override
    public Boolean update(Integer id, CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Category categoryOriginal = categoryOptional.get();
//            categoryOriginal.clone(categoryDto.getCategory());
//            categoryService.save(categoryOriginal);
            return true;
        } else
            return false;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
//            categoryService.delete(categoryOptional.get());
            return true;
        } else
            return false;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryService.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryService.findAll();
    }
}
