package com.dh.digitalCar.controllers;

import com.dh.digitalCar.FactoryService;
import com.dh.digitalCar.entities.Politics;
import com.dh.digitalCar.security.UserServiceSec;
import com.dh.digitalCar.services.PoliticsService;
import com.dh.digitalCar.services.TokenService;
import com.dh.digitalCar.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PoliticsController.class)
@Import({FactoryService.class, MockRepos.class})
class PoliticsControllerTest {
    private static Logger logger = Logger.getLogger(PoliticsControllerTest.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PoliticsService politicsService;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;
    @MockBean
    UserServiceSec userServiceSec;

    Optional<Politics> politicsOptional;

    @BeforeEach
    void setUp(){
        politicsOptional = Optional.of(factoryService.politics(false));
    }

    @Test
    void getPolitics() throws Exception {
        Mockito.when(politicsService.find()).thenReturn(politicsOptional);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/politics"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(politicsOptional.get()), response);
        logger.info(response);
    }

    @Test
    void updatePolitics() throws Exception {
        Mockito.when(politicsService.update(any(Politics.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/politics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(politicsOptional.get())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenNullPolitics_tryUpdatePolitics() throws Exception {
        Politics politics1 = new Politics();

        String response = mockMvc.perform(MockMvcRequestBuilders.put("/politics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(politics1)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        logger.info(response);
    }
}