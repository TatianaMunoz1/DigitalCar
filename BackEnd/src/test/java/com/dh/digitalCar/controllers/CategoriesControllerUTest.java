package com.dh.digitalCar.controllers;

import com.dh.digitalCar.dtos.CategoryDto;
import com.dh.digitalCar.entities.Category;
import com.dh.digitalCar.security.UserServiceSec;
import com.dh.digitalCar.services.CategoryService;
import com.dh.digitalCar.services.TokenService;
import com.dh.digitalCar.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CategoriesController.class)
class CategoriesControllerUTest {
    private static Logger logger = Logger.getLogger(CategoriesControllerUTest.class);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    CategoryService categoryService;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;
    @MockBean
    UserServiceSec userServiceSec;
    @MockBean
    PasswordEncoder passwordEncoder;

    @InjectMocks
    CategoriesController categoriesController;

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getAllCategories() throws Exception {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());
        categoryList.add(new Category());
        Mockito.when(categoryService.findAll()).thenReturn(categoryList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(categoryList), response);
        logger.info(response);
    }

    @Test
    void addCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto("title", "description", "www.img.com");
        Mockito.when(categoryService.save(any(CategoryDto.class))).thenReturn(true);

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/categories")
//                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)))
//                .param("name", "nameTest")
//                .param("lastName", "lastNameTest")
//                .param("email", "emailTest")
//                .param("password", "passTest"))
//                .andExpect(view().name("success"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        logger.info(response);
    }

    @Test
    void updateCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto("title", "description", "www.img.com");
        Mockito.when(categoryService.update(anyInt(), any(CategoryDto.class))).thenReturn(true);

        String response = mockMvc.perform(MockMvcRequestBuilders.put("/categories/1")
//                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)))
//                .param("name", "nameTest")
//                .param("lastName", "lastNameTest")
//                .param("email", "emailTest")
//                .param("password", "passTest"))
//                .andExpect(view().name("success"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        logger.info(response);
    }

    @Test
    void deleteCategory() throws Exception {
        Mockito.when(categoryService.deleteById(anyInt())).thenReturn(true);

        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/categories/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        logger.info(response);
    }
}