package com.dh.digitalCar.controllers;

import com.dh.digitalCar.FactoryService;
import com.dh.digitalCar.dtos.CarDto;
import com.dh.digitalCar.dtos.UserLoginDto;
import com.dh.digitalCar.dtos.UserTokenDto;
import com.dh.digitalCar.entities.Car;
import com.dh.digitalCar.entities.User;
import com.dh.digitalCar.security.UserServiceSec;
import com.dh.digitalCar.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarsController.class)
@Import({FactoryService.class, MockRepos.class})
@WithMockUser(username="name0@email.com", roles={"admin"})
class CarsControllerTest {
    private static Logger logger = Logger.getLogger(CarsControllerTest.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService carService;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;
    @MockBean
    UserServiceSec userServiceSec;
    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    void getAllCars() throws Exception {
        List<Car> carList = factoryService.cars(2, false);
        Mockito.when(carService.findAll()).thenReturn(carList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(carList), response);
        logger.info(response);
    }

    @Test
    void getCarByCityAndCategory() throws Exception {
        String cityName = "city0";
        String categoryTitle = "category0";
        List<Car> carList = factoryService.cars(3, false);
        Mockito.when(carService.findAllByCityAndCategory(cityName, categoryTitle))
                .thenReturn(carList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars/filter")
                .param("city", cityName)
                .param("category", categoryTitle))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(carList), response);
        logger.info(response);
    }

    @Test
    void getCarByCategory() throws Exception {
        String categoryTitle = "category0";
        List<Car> carList = factoryService.cars(2, false);
        Mockito.when(carService.findAllByCategory(categoryTitle))
                .thenReturn(carList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars/filter")
                .param("category", categoryTitle))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(carList), response);
        logger.info(response);
    }

    @Test
    void getCarByCity() throws Exception {
        String cityName = "city0";
        List<Car> carList = factoryService.cars(2, false);
        Mockito.when(carService.findAllByCity(cityName))
                .thenReturn(carList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars/filter")
                .param("city", cityName))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(carList), response);
        logger.info(response);
    }

    @Test
    void getCarByDate() throws Exception {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(1);
        List<CarDto> carDtoList = factoryService.carDtos(3, false);
        Mockito.when(carService.findAllByDate(start, end)).thenReturn(carDtoList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars/filter")
                .param("startDate", start.toString())
                .param("endDate", end.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(carDtoList), response);
        logger.info(response);
    }

//    @Test
//    void getCarByCityAndDate() throws Exception {
//        String cityName = "city0";
//        LocalDate start = LocalDate.now();
//        LocalDate end = LocalDate.now().plusDays(1);
//        List<Car> carList = FactoryService.cars(3, false);
//        Mockito.when(carService.findAllByCityAndCategory(cityName, categoryTitle))
//                .thenReturn(carList);
//
//        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars/filter")
//                .param("city", cityName)
//                .param("category", categoryTitle))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn().getResponse().getContentAsString();
//
//        assertEquals(objectMapper.writeValueAsString(carList), response);
//        logger.info(response);
//    }

    @Test
    void getCarById() throws Exception {
        Integer id = 1;
        List<Car> carList = factoryService.cars(1, false);
        Mockito.when(carService.findByID(id)).thenReturn(Optional.of(carList.get(0)));

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/cars/" + id))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(carList.get(0)), response);
        logger.info(response);
    }

    @Test
    void addCar() throws Exception {
        User user1 = factoryService.users(1, false, "admin").get(0);
        user1.setEmail("name0@email.com");
        UserTokenDto userTokenDto1 = new UserTokenDto(1, user1.getEmail(), LocalDateTime.now().plusMinutes(30));
        Mockito.when(tokenService.decodeToken(anyString())).thenReturn(userTokenDto1);
        Mockito.when(userServiceSec.loadUserByUsername(user1.getEmail())).thenReturn(user1);
        Mockito.when(userService.findByEmail(user1.getEmail())).thenReturn(Optional.of(user1));
        Mockito.when(passwordEncoder.matches(any(CharSequence.class), anyString())).thenReturn(true);

        List<Car> carList = factoryService.cars(1, false);
        Mockito.when(carService.save(any(Car.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                .header("Authorization", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carList.get(0))))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void givenNullCar_tryAddCar() throws Exception {
        User user1 = factoryService.users(1, false, "admin").get(0);
        user1.setEmail("name0@email.com");
        UserTokenDto userTokenDto1 = new UserTokenDto(1, user1.getEmail(), LocalDateTime.now().plusMinutes(30));
        Mockito.when(tokenService.decodeToken(anyString())).thenReturn(userTokenDto1);
        Mockito.when(userServiceSec.loadUserByUsername(user1.getEmail())).thenReturn(user1);
        Mockito.when(userService.findByEmail(user1.getEmail())).thenReturn(Optional.of(user1));
        Mockito.when(passwordEncoder.matches(any(CharSequence.class), anyString())).thenReturn(true);

        Car car1 = new Car();
        Mockito.when(carService.save(any(Car.class))).thenReturn(true);

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                .header("Authorization", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car1)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        logger.info(response);
    }

    @Test
    void updateCar() throws Exception {
        List<Car> carList = factoryService.cars(1, false);
        Mockito.when(carService.update(any(Car.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carList.get(0))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenNullCar_tryUpdateCar() throws Exception {
        Car car1 = new Car();
        Mockito.when(carService.update(any(Car.class))).thenReturn(true);

        String response = mockMvc.perform(MockMvcRequestBuilders.put("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car1)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        logger.info(response);
    }
}