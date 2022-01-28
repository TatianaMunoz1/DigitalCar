package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.CategoryDto;
import com.dh.digitalCar.entities.Category;
import com.dh.digitalCar.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainServiceTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MainService mainService;

    Category category1;

    @BeforeEach
    void setUp() {
        category1 = new Category("Economic", "Economic car",
                "www.google.com");
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void save() {
    }

    @Test
    void update() {
        category1 = categoryRepository.save(category1);
        CategoryDto categoryDto = new CategoryDto("Sedan economic", "Sedan economic",
                "www.google.com");

        Boolean boolActual = mainService.update(category1.getId(), categoryDto);

        category1 = categoryRepository.findById(category1.getId()).get();//assert de que existe??

        assertTrue(boolActual);
//        assertTrue(category1.equals(categoryDto.getCategory()));

        boolActual = mainService.update(-1, categoryDto);
        assertFalse(boolActual);
    }

    @Test
    void delete() {
        category1 = categoryRepository.save(category1);
        Boolean bool1 = mainService.delete(category1.getId());
        Optional<Category> optionalCategory1 = categoryRepository.findById(category1.getId());

        assertTrue(bool1);
        assertTrue(optionalCategory1.isEmpty());

        Boolean bool2 = mainService.delete(-1);
        Optional<Category> optionalCategory2 = categoryRepository.findById(-1);

        assertFalse(bool2);
        assertTrue(optionalCategory2.isEmpty());
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }
}