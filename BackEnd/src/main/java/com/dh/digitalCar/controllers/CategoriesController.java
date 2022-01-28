package com.dh.digitalCar.controllers;

import com.dh.digitalCar.dtos.CategoryDto;
import com.dh.digitalCar.services.servicesInterfaces.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoriesController {
    private ICategoryService categoryService;

    public CategoriesController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.save(categoryDto) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
