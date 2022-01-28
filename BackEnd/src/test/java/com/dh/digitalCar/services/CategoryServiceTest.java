package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.CategoryDto;
import com.dh.digitalCar.entities.Category;
import com.dh.digitalCar.repository.CategoryRepository;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class))
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class CategoryServiceTest {
    private static Logger logger = Logger.getLogger(CategoryServiceTest.class);
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;
    @MockBean
    EmailService emailService;

    CategoryDto category1;
    Category category2;
    Category category3;

    @BeforeEach
    void setUp() {
        category1 = new CategoryDto("titleTest","Auto economico",
                "www.google.com");
        category2 = new Category("Sedan", "Sedan",
                "www.google.com");
        category3 = new Category("Suv", "Suv desc",
                "www.google.com");
    }

//    @AfterEach
//    void tearDown() {
//        categoryRepository.deleteAll();
//    }

    @Test
    void save() {
        Boolean boolResponse = categoryService.save(category1);

        assertTrue(boolResponse);
        logger.info(categoryRepository.findAll());
    }

    @Test
    void update() {
        Category categoryAdded = categoryRepository.save(category2);
        logger.info(categoryRepository.findAll());

        CategoryDto categoryDto = new CategoryDto("titleUpdated", "descUpdated", category2.getImgUrl());
        categoryService.update(category2.getId(), categoryDto);
        logger.info(categoryRepository.findAll());

        Optional<Category> optionalCategory = categoryRepository.findById(categoryAdded.getId());
        Category categoryUpdated = optionalCategory.get();

        assertEquals(categoryUpdated.getTitle(), categoryDto.getTitle());
        logger.info(categoryRepository.findAll());
    }

    @Test
    void findById() {
        Category categoryAdded = categoryRepository.save(category2);
        Optional<Category> optionalCategory = categoryService.findById(categoryAdded.getId());

        assertTrue(optionalCategory.isPresent());
        assertEquals(categoryAdded.getId(), optionalCategory.get().getId());
        logger.info(optionalCategory.get());
    }

    @Test
    void findAll() {
        List<Category> categoryList1 = new ArrayList<>();
        Category categoryAdded1 = categoryRepository.save(category2);
        Category categoryAdded2 = categoryRepository.save(category3);
        categoryList1.add(categoryAdded1);
        categoryList1.add(categoryAdded2);

        List<Category> categoryList2 = categoryRepository.findAll();
        assertTrue(categoryList1.equals(categoryList2));
        logger.info(categoryList2);
    }

    @Test
    void delete() {
        Category categoryAdded = categoryRepository.save(category2);
        logger.info(categoryRepository.findAll());
        categoryService.deleteById(categoryAdded.getId());
        Optional<Category> optionalCategory = categoryRepository.findById(categoryAdded.getId());

        assertTrue(optionalCategory.isEmpty());
        logger.info(categoryRepository.findAll());
    }
}