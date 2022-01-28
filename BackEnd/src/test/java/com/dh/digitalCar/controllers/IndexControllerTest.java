package com.dh.digitalCar.controllers;

import com.dh.digitalCar.dtos.UserLoginDto;
import com.dh.digitalCar.entities.User;
import com.dh.digitalCar.security.UserServiceSec;
import com.dh.digitalCar.FactoryService;
import com.dh.digitalCar.services.TokenService;
import com.dh.digitalCar.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IndexController.class)
@Import(FactoryService.class)
@WithMockUser(username="name0@email.com", roles={"user"})
class IndexControllerTest {
    private static Logger logger = Logger.getLogger(IndexControllerTest.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;
    @MockBean
    UserServiceSec userServiceSec;
    @MockBean
    PasswordEncoder passwordEncoder;

    User user1;
    Optional<UserLoginDto> userLoginDto1;

    @BeforeEach
    void setUp() {
        user1 = factoryService.users(1, false, "user").get(0);
        userLoginDto1 = Optional.of(new UserLoginDto(user1.getId(), user1.getName(), user1.getLastName(), user1.getEmail(), "token", user1.getRole().getName()));

        Mockito.when(userService.login(user1)).thenReturn(userLoginDto1);
        Mockito.when(userServiceSec.loadUserByUsername(user1.getEmail())).thenReturn(user1);
        Mockito.when(passwordEncoder.matches(ArgumentMatchers.any(CharSequence.class), eq(user1.getPassword()))).thenReturn(true);
    }
////
////    @AfterEach
////    void tearDown() {
////    }
//
    @Test
    void registerUser() throws Exception {
        Mockito.when(userService.save(user1)).thenReturn(Optional.of(user1));

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/register")
//                .accept(MediaType.TEXT_HTML)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(userLoginDto1), response);
        logger.info(response);
    }

    @Test
    void loginUser() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(userLoginDto1), response);
        logger.info(response);
    }
}