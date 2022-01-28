package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.CategoryDto;
import com.dh.digitalCar.entities.Category;
import com.dh.digitalCar.repository.CategoryRepository;
import com.dh.digitalCar.services.servicesInterfaces.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private CategoryRepository repository;
    private ModelMapper modelMapper;

    public CategoryService(CategoryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean save(CategoryDto categoryDto) {
        repository.save(modelMapper.map(categoryDto, Category.class));
        return true;
    }

    @Override
    public Boolean update(Integer id, CategoryDto categoryDto) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            Category categoryOriginal = categoryOptional.get();
            categoryOriginal.clone(categoryDto);
//            System.out.println(categoryOriginal);
//            categoryOriginal = (modelMapper.map(categoryDto, Category.class));
//            System.out.println(categoryOriginal);
            repository.save(categoryOriginal);
            return true;
        } else
            return false;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        } else
            return false;
    }
}
