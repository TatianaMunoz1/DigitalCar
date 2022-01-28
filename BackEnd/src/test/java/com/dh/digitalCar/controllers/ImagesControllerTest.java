package com.dh.digitalCar.controllers;

import com.dh.digitalCar.FactoryService;
import com.dh.digitalCar.entities.Image;
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

@WebMvcTest(ImagesController.class)
@Import({FactoryService.class, MockRepos.class})
class ImagesControllerTest {
    private static Logger logger = Logger.getLogger(ImagesControllerTest.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ImageService imageService;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;
    @MockBean
    UserServiceSec userServiceSec;

    @Test
    void getAllImages() throws Exception {
        List<Image> imageList = factoryService.images(2, false);
        Mockito.when(imageService.findAll()).thenReturn(imageList);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/images"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(imageList), response);
        logger.info(response);
    }

    @Test
    void addImage() throws Exception {
        List<Image> imageList = factoryService.images(1, false);
        Mockito.when(imageService.save(any(Image.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imageList.get(0))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenNullImage_tryAddImage() throws Exception {
        Image image1 = new Image();

        mockMvc.perform(MockMvcRequestBuilders.post("/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(image1)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}