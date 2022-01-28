package com.dh.digitalCar.controllers;

import com.dh.digitalCar.FactoryService;
import com.dh.digitalCar.entities.Feature;
import com.dh.digitalCar.security.UserServiceSec;
import com.dh.digitalCar.services.*;
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

@WebMvcTest(FeaturesController.class)
@Import({FactoryService.class, MockRepos.class})
class FeaturesControllerTest {
    private static Logger logger = Logger.getLogger(FeaturesControllerTest.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FeatureService featureService;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;
    @MockBean
    UserServiceSec userServiceSec;

    @Test
    void getAllFeatures() throws Exception {
        List<Feature> featureList = factoryService.features(2, false);
        Mockito.when(featureService.findAll()).thenReturn(featureList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/features"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(featureList), response);
        logger.info(response);
    }

    @Test
    void addFeature() throws Exception {
        List<Feature> featureList = factoryService.features(1, false);
        Mockito.when(featureService.save(any(Feature.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/features")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(featureList.get(0))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenNullFeature_tryAddFeature() throws Exception {
        Feature feature1 = new Feature();

        mockMvc.perform(MockMvcRequestBuilders.post("/features")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feature1)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}