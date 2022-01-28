package com.dh.digitalCar.controllers;

import com.dh.digitalCar.FactoryService;
import com.dh.digitalCar.entities.City;
import com.dh.digitalCar.security.UserServiceSec;
import com.dh.digitalCar.services.CityService;
import com.dh.digitalCar.services.TokenService;
import com.dh.digitalCar.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CitiesController.class)
@Import({FactoryService.class, MockRepos.class})
class CitiesControllerTest {
    private static Logger logger = Logger.getLogger(CitiesControllerTest.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CityService cityService;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;
    @MockBean
    UserServiceSec userServiceSec;

    @Test
    void getAllCities() throws Exception {
        List<City> cityList = factoryService.cities(2, false);
        Mockito.when(cityService.findAll()).thenReturn(cityList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cities"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(cityList), response);
        logger.info(response);
    }

    @Test
    void addCity() throws Exception {
        List<City> cityList = factoryService.cities(1, false);
        Mockito.when(cityService.save(any(City.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cityList.get(0))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenNullCity_tryAdd() throws Exception {
        City city1 = new City();

        mockMvc.perform(MockMvcRequestBuilders.post("/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(city1)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}